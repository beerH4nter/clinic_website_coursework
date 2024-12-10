import React from "react";
import { BrowserRouter as Router } from "react-router-dom";
import AppRoutes from "./routes/AppRoutes";
import Navbar from "./components/navbar/Navbar"; // Подключаем Navbar

const App = () => {
    return (
        <Router>
            {/* Глобальный Navbar */}
            <Navbar />
            {/* Подключение маршрутов */}
            <AppRoutes />
        </Router>
    );
};

export default App;
