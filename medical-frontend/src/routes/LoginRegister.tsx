import React, { useState } from 'react'
import { Box, TextField, Button, Paper } from '@mui/material'
import { loginPatient, registerPatient } from '../services/api.ts'
import { useNavigate } from 'react-router-dom'

function LoginRegister() {
  const navigate = useNavigate()
  const [isLogin, setIsLogin] = useState(true)
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [name, setName] = useState('')
  const [surname, setSurname] = useState('')
  const [patronymic, setPatronymic] = useState('')
  const [dob, setDob] = useState('')

  const handleLogin = async () => {
    try {
      const res = await loginPatient({ email, password })
      localStorage.setItem('patient', JSON.stringify(res.data))
      navigate('/personal')
    } catch (e) {
      alert('Неверные данные')
    }
  }

  const handleRegister = async () => {
    try {
      const res = await registerPatient({
        name,
        surname,
        patronymic,
        dateOfBirth: dob,
        email,
        password
      })
      localStorage.setItem('patient', JSON.stringify(res.data))
      navigate('/personal')
    } catch (e) {
      alert('Ошибка регистрации')
    }
  }

  return (
    <Box display="flex" justifyContent="center">
      <Paper sx={{ p: 4, maxWidth: 400 }}>
        <Box display="flex" justifyContent="space-between" mb={2}>
          <Button variant={isLogin ? 'contained' : 'outlined'} onClick={() => setIsLogin(true)}>
            Вход
          </Button>
          <Button variant={!isLogin ? 'contained' : 'outlined'} onClick={() => setIsLogin(false)}>
            Регистрация
          </Button>
        </Box>
        {isLogin ? (
          <Box display="flex" flexDirection="column" gap={2}>
            <TextField label="Email" value={email} onChange={e => setEmail(e.target.value)} />
            <TextField label="Пароль" type="password" value={password} onChange={e => setPassword(e.target.value)} />
            <Button variant="contained" onClick={handleLogin}>Войти</Button>
          </Box>
        ) : (
          <Box display="flex" flexDirection="column" gap={2}>
            <TextField label="Имя" value={name} onChange={e => setName(e.target.value)} />
            <TextField label="Фамилия" value={surname} onChange={e => setSurname(e.target.value)} />
            <TextField label="Отчество" value={patronymic} onChange={e => setPatronymic(e.target.value)} />
            <TextField label="Дата рождения" value={dob} onChange={e => setDob(e.target.value)} />
            <TextField label="Email" value={email} onChange={e => setEmail(e.target.value)} />
            <TextField label="Пароль" type="password" value={password} onChange={e => setPassword(e.target.value)} />
            <Button variant="contained" onClick={handleRegister}>Зарегистрироваться</Button>
          </Box>
        )}
      </Paper>
    </Box>
  )
}

export default LoginRegister
