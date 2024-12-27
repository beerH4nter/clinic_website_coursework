import React, { useState } from 'react'
import { Outlet, useNavigate } from 'react-router-dom'
import { AppBar, Toolbar, Typography, Button, Box, Slide } from '@mui/material'

function Layout() {
  const navigate = useNavigate()
  const [show] = useState(true)
  return (
    <Box>
      <AppBar position="sticky">
        <Toolbar>
          <Typography sx={{ flexGrow: 1, cursor: 'pointer' }} onClick={() => navigate('/')}>
            Medical Clinic
          </Typography>
          <Button color="inherit" onClick={() => navigate('/')}>Главная</Button>
          <Button color="inherit" onClick={() => navigate('/service')}>Услуги</Button>
          <Button color="inherit" onClick={() => navigate('/sale')}>Акции</Button>
          <Button color="inherit" onClick={() => navigate('/auth')}>Личный кабинет</Button>
          <Button color="inherit" onClick={() => navigate('/admin')}>Админ</Button>
        </Toolbar>
      </AppBar>
      <Slide direction="up" in={show} mountOnEnter unmountOnExit>
        <Box sx={{ p: 2 }}>
          <Outlet />
        </Box>
      </Slide>
    </Box>
  )
}

export default Layout
