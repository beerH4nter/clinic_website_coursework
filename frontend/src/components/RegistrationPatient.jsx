import React, { useState } from "react";

const RegistrationPatient = () => {
    const [formData, setFormData] = useState({
        name: "",
        surname: "",
        patronymic: "",
        dateOfBirth: "",
        email: "",
        password: "",
    });

    const [errors, setErrors] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const formatDate = (inputDate) => {
        const date = new Date(inputDate); // Преобразуем строку в объект Date
        const day = String(date.getDate()).padStart(2, "0"); // Добавляем ведущий ноль к дню
        const month = String(date.getMonth() + 1).padStart(2, "0"); // Месяцы с 0, поэтому добавляем 1
        const year = date.getFullYear(); // Получаем год
        return `${day}.${month}.${year}`; // Собираем в нужном формате
    };

    const validate = () => {
        const newErrors = {};
        if (!formData.name.trim()) newErrors.name = "Имя обязательно.";
        if (!formData.surname.trim()) newErrors.surname = "Фамилия обязательна.";
        if (!formData.dateOfBirth.trim()) newErrors.dateOfBirth = "Дата рождения обязательна.";
        if (!formData.email.trim()) newErrors.email = "Email обязателен.";
        else if (!/\S+@\S+\.\S+/.test(formData.email)) newErrors.email = "Некорректный email.";
        if (!formData.password.trim()) newErrors.password = "Пароль обязателен.";
        else if (formData.password.length < 6) newErrors.password = "Пароль должен быть не менее 6 символов.";

        return newErrors;
    };

    const handleSubmit = async (e) => {
        e.preventDefault(); // Отменяем стандартное поведение формы

        const newErrors = validate(); // Проверяем ошибки
        if (Object.keys(newErrors).length === 0) {
            const formattedData = {
                ...formData,
                dateOfBirth: formatDate(formData.dateOfBirth), // Преобразуем дату
            };
            try {
                const response = await fetch("/patient/save", {
                    method: "POST", // Тип запроса
                    headers: {
                        "Content-Type": "application/json", // Указываем формат отправляемых данных
                    },
                    body: JSON.stringify(formattedData), // Преобразуем данные формы в JSON
                });

                if (response.ok) {
                    const result = await response.json(); // Получаем ответ от сервера
                    console.log("Registration successful:", result);
                    setIsSubmitted(true); // Устанавливаем статус успешной регистрации
                } else {
                    const errorData = await response.json();
                    console.error("Error:", errorData);
                    setErrors({ server: "Ошибка регистрации. Попробуйте еще раз." });
                }
            } catch (error) {
                console.error("Network error:", error);
                setErrors({ server: "Сетевая ошибка. Проверьте подключение к интернету." });
            }
        } else {
            setErrors(newErrors); // Устанавливаем ошибки для отображения
        }
    };

    return (
        <div>
            {isSubmitted ? (
                <p>Регистрация прошла успешно!</p>
            ) : (
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Имя:</label>
                        <input type="text" name="name" value={formData.name} onChange={handleInputChange} />
                        {errors.name && <span style={{ color: "red" }}>{errors.name}</span>}
                    </div>
                    <div>
                        <label>Фамилия:</label>
                        <input type="text" name="surname" value={formData.surname} onChange={handleInputChange} />
                        {errors.surname && <span style={{ color: "red" }}>{errors.surname}</span>}
                    </div>
                    <div>
                        <label>Отчество:</label>
                        <input type="text" name="patronymic" value={formData.patronymic} onChange={handleInputChange} />
                    </div>
                    <div>
                        <label>Дата рождения:</label>
                        <input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleInputChange} />
                        {errors.dateOfBirth && <span style={{ color: "red" }}>{errors.dateOfBirth}</span>}
                    </div>
                    <div>
                        <label>Email:</label>
                        <input type="email" name="email" value={formData.email} onChange={handleInputChange} />
                        {errors.email && <span style={{ color: "red" }}>{errors.email}</span>}
                    </div>
                    <div>
                        <label>Пароль:</label>
                        <input type="password" name="password" value={formData.password} onChange={handleInputChange} />
                        {errors.password && <span style={{ color: "red" }}>{errors.password}</span>}
                    </div>
                    <button type="submit">Зарегистрироваться</button>
                </form>
            )}
        </div>
    );
};

export default RegistrationPatient;
