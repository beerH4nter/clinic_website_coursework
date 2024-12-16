import React from "react";
import { NavLink } from "react-router-dom";
import { List, ListItem, ListItemText, Drawer, Toolbar } from "@mui/material";

const ProfileSidebar = () => {
    const menuItems = [
        { text: "Мои записи", path: "/profile/appointments" },
        { text: "Анализы", path: "/profile/tests" },
        { text: "Уведомления", path: "/profile/notifications" },
        { text: "Личные данные", path: "/profile/personal-data" },
        { text: "Рецепты", path: "/profile/prescriptions" },
    ];

    return (
        <Drawer variant="permanent" sx={{ width: 240 }}>
            <Toolbar />
            <List>
                {menuItems.map((item, index) => (
                    <ListItem 
                        button 
                        component={NavLink} 
                        to={item.path} 
                        key={index}
                        sx={{ textDecoration: 'none' }}
                    >
                        <ListItemText primary={item.text} />
                    </ListItem>
                ))}
            </List>
        </Drawer>
    );
};

export default ProfileSidebar;
