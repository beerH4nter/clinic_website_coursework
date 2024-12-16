import React from "react";
import { Outlet } from "react-router-dom";
import { Box, CssBaseline } from "@mui/material";
import ProfileSidebar from "../../components/sidebar/ProfileSidebar";

const ProfilePage = () => {
    return (
        <Box sx={{ display: "flex" }}>
            <CssBaseline />
            <ProfileSidebar />
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <Outlet /> {/* Здесь будет содержимое дочерних маршрутов */}
            </Box>
        </Box>
    );
};

export default ProfilePage;
