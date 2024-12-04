import React from "react";
import { BrowserRouter as Router, Link } from "react-router-dom";
import AppRoutes from "./routes/AppRoutes";

const App = () => {
    return (
        <Router>
            <header>
                <nav>
                    <Link to="/">Главная</Link>
                    <Link to="/register">Регистрация</Link>
                </nav>
            </header>

            {/* Подключение маршрутов */}
            <AppRoutes />
        </Router>
    );
};

export default App;
