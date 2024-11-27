import React, { useState } from "react";

const GetAllPatients = () => {
  const [patients, setPatients] = useState([]);
  const [error, setError] = useState("");

  const fetchPatients = async () => {
    try {
      const res = await fetch("/patient/getAll");
      const data = await res.json();
      setPatients(data);
      setError("");
    } catch (err) {
      setError("Ошибка при получении пациентов");
    }
  };

  return (
    <div>
      <h2>Список пациентов</h2>
      <button onClick={fetchPatients}>Получить всех пациентов</button>
      {error && <p>{error}</p>}
      <ul>
        {patients.map((patient) => (
          <li key={patient.id}>
            <p>
              <strong>Имя:</strong> {patient.name}
            </p>
            <p>
              <strong>Фамилия:</strong> {patient.surname}
            </p>
            <p>
              <strong>Отчество:</strong> {patient.patronymic}
            </p>
            <p>
              <strong>Дата рождения:</strong> {patient.dateOfBirth}
            </p>
            <p>
              <strong>Email:</strong> {patient.email}
            </p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default GetAllPatients;
