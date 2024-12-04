import React, { useState } from "react";

const FindPatientById = () => {
    const [id, setId] = useState("");
    const [patient, setPatient] = useState(null);
    const [error, setError] = useState("");

    const handleSearch = async () => {
        try {
            const res = await fetch(`/patient/findById?id=${id}`);
            if (!res.ok) throw new Error("Пациент не найден");
            const data = await res.json();
            setPatient(data);
            setError("");
        } catch (err) {
            setPatient(null);
            setError(err.message);
        }
    };

    return (
        <div>
            <h2>Поиск пациента по ID</h2>
            <input type="number" placeholder="Введите ID пациента" value={id} onChange={(e) => setId(e.target.value)} />
            <button onClick={handleSearch}>Найти</button>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {patient && (
                <div>
                    <h3>Информация о пациенте</h3>
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
                </div>
            )}
        </div>
    );
};

export default FindPatientById;
