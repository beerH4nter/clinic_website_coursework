import React from "react";
import { Routes, Route } from "react-router-dom";
import HomePage from "../pages/home/HomePage";
import RegistrationPage from "../pages/registration/RegistrationPage";
// import ServicesPage from "../pages/services/ServicesPage"; // Страница услуг
// import PromotionsPage from "../pages/promotions/PromotionsPage"; // Страница акций
// import ProfilePage from "../pages/profile/ProfilePage"; // Личный кабинет

const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage />} /> {/* Главная */}
            <Route path="/register" element={<RegistrationPage />} /> {/* Регистрация */}
            {/* <Route path="/services" element={<ServicesPage />} /> Услуги */}
            {/* <Route path="/promotions" element={<PromotionsPage />} /> Акции */}
            {/* <Route path="/profile" element={<ProfilePage />} /> Личный кабинет */}
        </Routes>
    );
};

export default AppRoutes;
