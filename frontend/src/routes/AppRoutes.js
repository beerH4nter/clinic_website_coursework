// src/routes/routes.jsx
import React from "react";
import { Routes, Route } from "react-router-dom";
import HomePage from "../pages/home/HomePage"; // Пример главной страницы
import RegistrationPage from "../pages/registration/RegistrationPage"; // Страница регистрации

const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage />} /> {/* Главная */}
            <Route path="/register" element={<RegistrationPage />} /> {/* Регистрация */}
            {/* Добавьте другие маршруты */}
        </Routes>
    );
};

export default AppRoutes;
