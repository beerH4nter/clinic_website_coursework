import React from "react";
import { Routes, Route } from "react-router-dom";
import HomePage from "../pages/home/HomePage";
import RegistrationPage from "../pages/registration/RegistrationPage";
// import ServicesPage from "../pages/services/ServicesPage"; // Страница услуг
// import PromotionsPage from "../pages/promotions/PromotionsPage"; // Страница акций
import ProfilePage from "../pages/profile/ProfilePage"; // Личный кабинет
import AppointmentsPage from "../pages/appointments/AppointmentsPage"
import TestsPage from "../pages/tests/TestsPage"
import NotificationsPage from "../pages/notifications/NotificationsPage"
import PersonalDataPage from "../pages/personal-data/PersonalDataPage"
import PrescriptionsPage from "../pages/prescriptions/PrescriptionsPage"

const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage />} /> {/* Главная */}
            <Route path="/register" element={<RegistrationPage />} /> 
            
            {/* <Route path="/services" element={<ServicesPage />} /> Услуги */}
            {/* <Route path="/promotions" element={<PromotionsPage />} /> Акции */}
            <Route path="/profile" element={<ProfilePage />}>
                <Route path="appointments" element={<AppointmentsPage />} />
                <Route path="tests" element={<TestsPage />} />
                <Route path="notifications" element={<NotificationsPage />} />
                <Route path="personal-data" element={<PersonalDataPage />} />
                <Route path="prescriptions" element={<PrescriptionsPage />} />
            </Route>
        </Routes>
    );
};

export default AppRoutes;
