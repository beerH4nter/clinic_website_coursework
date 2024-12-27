import React, { useState } from 'react'
import { Box, Paper, TextField, Button, Typography } from '@mui/material'

function AdminPanel() {
  const [input, setInput] = useState('')
  const [access, setAccess] = useState(false)
  const handleSubmit = () => {
    if (input === 'admin') setAccess(true)
    else alert('Неверный пароль')
  }
  return access ? (
    <Box sx={{ p: 2 }} display="flex" flexDirection="column" gap={2}>
      <Typography variant="h5">Админ панель</Typography>
      <Typography>Здесь можно добавить или отредактировать услуги, акции, смотреть пользователей и т.д.</Typography>
      <Typography>Реализация может быть расширена статично на фронте</Typography>
    </Box>
  ) : (
    <Box display="flex" justifyContent="center" mt={4}>
      <Paper sx={{ p: 4 }}>
        <TextField label="Пароль" value={input} onChange={e => setInput(e.target.value)} />
        <Button variant="contained" onClick={handleSubmit} sx={{ mt: 2 }}>Войти</Button>
      </Paper>
    </Box>
  )
}

export default AdminPanel
