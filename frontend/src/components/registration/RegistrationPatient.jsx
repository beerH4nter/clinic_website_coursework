import React, { useState } from "react";
import { Box, Button, TextField, Typography, Alert, Grid } from "@mui/material";

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
        const date = new Date(inputDate);
        const day = String(date.getDate()).padStart(2, "0");
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const year = date.getFullYear();
        return `${day}.${month}.${year}`;
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
        e.preventDefault();

        const newErrors = validate();
        if (Object.keys(newErrors).length === 0) {
            const formattedData = {
                ...formData,
                dateOfBirth: formatDate(formData.dateOfBirth),
            };
            try {
                const response = await fetch("/patient/save", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(formattedData),
                });

                if (response.ok) {
                    const result = await response.json();
                    console.log("Registration successful:", result);
                    setIsSubmitted(true);
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
            setErrors(newErrors);
        }
    };

    return (
        <Box sx={{ maxWidth: 500, mx: "auto", mt: 5 }}>
            {isSubmitted ? (
                <Alert severity="success">Регистрация прошла успешно!</Alert>
            ) : (
                <form onSubmit={handleSubmit}>
                    <Typography variant="h4" component="h1" gutterBottom>
                        Регистрация
                    </Typography>

                    {errors.server && (
                        <Alert severity="error" sx={{ mb: 2 }}>
                            {errors.server}
                        </Alert>
                    )}

                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Имя" name="name" value={formData.name} onChange={handleInputChange} error={!!errors.name} helperText={errors.name} />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField fullWidth label="Фамилия" name="surname" value={formData.surname} onChange={handleInputChange} error={!!errors.surname} helperText={errors.surname} />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField fullWidth label="Отчество" name="patronymic" value={formData.patronymic} onChange={handleInputChange} />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                type="date"
                                label="Дата рождения"
                                name="dateOfBirth"
                                value={formData.dateOfBirth}
                                onChange={handleInputChange}
                                error={!!errors.dateOfBirth}
                                helperText={errors.dateOfBirth}
                                InputLabelProps={{ shrink: true }}
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField fullWidth label="Email" name="email" type="email" value={formData.email} onChange={handleInputChange} error={!!errors.email} helperText={errors.email} />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                label="Пароль"
                                name="password"
                                type="password"
                                value={formData.password}
                                onChange={handleInputChange}
                                error={!!errors.password}
                                helperText={errors.password}
                            />
                        </Grid>
                    </Grid>

                    <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 3 }}>
                        Зарегистрироваться
                    </Button>
                </form>
            )}
        </Box>
    );
};

export default RegistrationPatient;
