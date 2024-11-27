import React, { useState } from "react";

const AddPatientForm = () => {
  const [patient, setPatient] = useState({
    name: "",
    surname: "",
    patronymic: "",
    dateOfBirth: "",
    email: "",
    password: "",
  });
  const [response, setResponse] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPatient((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("/patient/save", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patient),
      });
      const data = await res.json();
      setResponse(`Пациент сохранён: ${JSON.stringify(data)}`);
    } catch (error) {
      setResponse("Ошибка при сохранении пациента");
    }
  };

  return (
    <div>
      <h2>Добавить пациента</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Имя"
          value={patient.name}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="surname"
          placeholder="Фамилия"
          value={patient.surname}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="patronymic"
          placeholder="Отчество"
          value={patient.patronymic}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="dateOfBirth"
          placeholder="Дата рождения (дд.мм.гггг)"
          value={patient.dateOfBirth}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={patient.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Пароль"
          value={patient.password}
          onChange={handleChange}
          required
        />
        <button type="submit">Сохранить</button>
      </form>
      {response && <p>{response}</p>}
    </div>
  );
};

export default AddPatientForm;
