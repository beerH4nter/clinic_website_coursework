import React, { useState, useEffect } from "react";
import { Box, Typography, Tabs, Tab, Card, CardContent, CircularProgress, Alert, Grid } from "@mui/material";
import { useUser } from "../../context/UserContext";

const AppointmentsPage = () => {
    const { user } = useUser();
    const [appointments, setAppointments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [tab, setTab] = useState("current");

    const fetchAppointments = async (statusFilter) => {
        if (!user || !user.id) return;

        setLoading(true);
        setError(null);

        try {
            const response = await fetch(`http://localhost:8080/appointment/findByPatient?id=${user.id}`);
            console.log("Response status:", response.status); // Для отладки

            if (!response.ok) {
                throw new Error(`Ошибка: ${response.statusText}`);
            }

            const data = await response.json();
            console.log("Response data:", data); // Для отладки

            if (!Array.isArray(data)) {
                throw new Error("Некорректный формат данных от сервера.");
            }

            const filteredData =
                statusFilter === "current" ? data.filter((item) => ["SHEDULED", "IN_PROGRESS"].includes(item.status)) : data.filter((item) => ["COMPLETED", "CANCELED"].includes(item.status));

            setAppointments(filteredData);
        } catch (err) {
            console.error("Ошибка при получении записей:", err.message);
            setError(err.message);
        } finally {
            setLoading(false);
            console.log("Загрузка завершена"); // Для отладки
        }
    };

    useEffect(() => {
        // Убедитесь, что данные загружаются только при изменении таба и есть пользователь
        if (user && user.id) {
            fetchAppointments(tab);
        }
    }, [tab, user]); // Добавляем зависимость от user, чтобы не было бесконечной загрузки

    const handleTabChange = (event, newValue) => {
        setTab(newValue);
    };

    return (
        <Box sx={{ padding: 3 }}>
            <Typography variant="h4" gutterBottom>
                Ваши записи
            </Typography>

            {/* Табы для переключения между текущими и прошедшими записями */}
            <Tabs
                value={tab}
                onChange={handleTabChange}
                sx={{
                    marginBottom: 3,
                    borderBottom: "1px solid #ccc",
                }}>
                <Tab label="Текущие записи" value="current" />
                <Tab label="Прошедшие записи" value="past" />
            </Tabs>

            {loading && (
                <Box sx={{ display: "flex", justifyContent: "center", mt: 3 }}>
                    <CircularProgress />
                </Box>
            )}

            {error && <Alert severity="error">{error}</Alert>}

            {!loading && !error && (
                <Grid container spacing={2}>
                    {appointments.length > 0 ? (
                        appointments.map((appointment) => (
                            <Grid item xs={12} sm={6} md={4} key={appointment.id}>
                                <Card
                                    sx={{
                                        border: "1px solid #ddd",
                                        borderRadius: 2,
                                    }}>
                                    <CardContent>
                                        <Typography variant="h6" gutterBottom>
                                            {new Date(appointment.date).toLocaleDateString()} - {appointment.time}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Статус:{" "}
                                            <b>
                                                {appointment.status === "SHEDULED"
                                                    ? "Запланировано"
                                                    : appointment.status === "COMPLETED"
                                                    ? "Завершено"
                                                    : appointment.status === "CANCELED"
                                                    ? "Отменено"
                                                    : "В процессе"}
                                            </b>
                                        </Typography>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))
                    ) : (
                        <Typography variant="body1" align="center" sx={{ width: "100%", mt: 3 }}>
                            Нет записей для отображения.
                        </Typography>
                    )}
                </Grid>
            )}
        </Box>
    );
};

export default AppointmentsPage;
