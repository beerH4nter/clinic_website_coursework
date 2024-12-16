import React from "react";
import { BrowserRouter as Router } from "react-router-dom";
import AppRoutes from "./routes/AppRoutes";
import Navbar from "./components/navbar/Navbar";
import { UserContextProvider } from "./context/UserContext"; // Исправленный импорт

const App = () => {
    return (
        <UserContextProvider>
            <Router>
                <Navbar />
                <AppRoutes />
            </Router>
        </UserContextProvider>
    );
};

export default App;
