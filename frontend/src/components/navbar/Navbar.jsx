import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AppBar, Toolbar, Typography, Button, Dialog, TextField, Box } from "@mui/material";

const Navbar = () => {
    const [isAuth, setIsAuth] = useState(false);
    const [open, setOpen] = useState(false);
    const [loginData, setLoginData] = useState({ email: "", password: "" });
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setLoginData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleLogin = async () => {
        try {
            const response = await fetch(`http://localhost:8080/patient/findByEmail?email=${loginData.email}`);
            if (response.ok) {
                const user = await response.json();
                if (user.password === loginData.password) {
                    setIsAuth(true);
                    setOpen(false);
                    navigate("/profile");
                } else {
                    setError("Неверный пароль.");
                }
            } else {
                setError("Пользователь не найден.");
            }
        } catch (err) {
            setError("Ошибка сети. Попробуйте позже.");
        }
    };

    const handleProfileClick = () => {
        if (!isAuth) {
            setOpen(true);
        } else {
            navigate("/profile");
        }
    };

    return (
        <>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        My App
                    </Typography>
                    <Button color="inherit" component={Link} to="/">
                        Главная
                    </Button>
                    <Button color="inherit" component={Link} to="/services">
                        Услуги
                    </Button>
                    <Button color="inherit" component={Link} to="/promotions">
                        Акции
                    </Button>
                    <Button color="inherit" onClick={handleProfileClick}>
                        Личный кабинет
                    </Button>
                </Toolbar>
            </AppBar>

            {/* Диалог авторизации */}
            <Dialog open={open} onClose={() => setOpen(false)}>
                <Box sx={{ padding: 3, width: 300 }}>
                    <Typography variant="h6" gutterBottom>
                        Авторизация
                    </Typography>
                    {error && <Typography color="error">{error}</Typography>}
                    <TextField label="Email" name="email" value={loginData.email} onChange={handleInputChange} fullWidth margin="normal" variant="outlined" />
                    <TextField label="Пароль" name="password" type="password" value={loginData.password} onChange={handleInputChange} fullWidth margin="normal" variant="outlined" />
                    <Button variant="contained" color="primary" fullWidth onClick={handleLogin} sx={{ mt: 2 }}>
                        Вход
                    </Button>
                    <Button
                        variant="contained"
                        color="primary"
                        fullWidth
                        onClick={() => {
                            setOpen(false);
                            navigate("/register");
                        }}
                        sx={{ mt: 2 }}>
                        Регистрация
                    </Button>
                </Box>
            </Dialog>
        </>
    );
};

export default Navbar;
