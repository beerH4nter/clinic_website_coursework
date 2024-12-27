#!/bin/bash

# Этот скрипт создаст полностью рабочий React-проект (Create React App-совместимый)
# со структурой, необходимой для запуска и сборки (включая public/index.html),
# а также с нужными контроллерами/файлами из предыдущих сообщений.

# Создаем папку проекта и заходим в нее
mkdir -p medical-frontend
cd medical-frontend

# Создаем файл build.sh
cat << 'EOF' > build.sh
#!/bin/bash
npm install
npm run build
EOF
chmod +x build.sh

# Создаем package.json со всеми нужными зависимостями (включая react-scripts).
cat << 'EOF' > package.json
{
  "name": "medical-frontend",
  "version": "1.0.0",
  "private": true,
  "dependencies": {
    "@emotion/react": "^11.10.6",
    "@emotion/styled": "^11.10.6",
    "@mui/icons-material": "^5.11.16",
    "@mui/lab": "^5.0.0-alpha.129",
    "@mui/material": "^5.11.16",
    "@types/jest": "^29.5.2",
    "@types/node": "^18.15.11",
    "@types/react": "^18.0.28",
    "@types/react-dom": "^18.0.11",
    "axios": "^1.4.0",
    "dayjs": "^1.11.9",
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-router-dom": "^6.14.1",
    "react-scripts": "^5.0.1",
    "typescript": "^4.9.5",
    "web-vitals": "^3.3.1"
  },
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test"
  }
}
EOF

# Создаем нужные директории
mkdir -p public
mkdir -p src
mkdir -p src/routes
mkdir -p src/services
mkdir -p src/components

# Создаем public/index.html
cat << 'EOF' > public/index.html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Medical Frontend</title>
  </head>
  <body>
    <div id="root"></div>
  </body>
</html>
EOF

# Создаем react-app-env.d.ts
cat << 'EOF' > src/react-app-env.d.ts
/// <reference types="react-scripts" />
EOF

# Создаем App.css (простая анимация)
cat << 'EOF' > src/App.css
body {
  margin: 0;
  padding: 0;
  font-family: 'Roboto', sans-serif;
  background: #f0f0f0;
  animation: bgFade 5s infinite alternate ease-in-out;
}

@keyframes bgFade {
  0% { background-color: #f0f0f0; }
  100% { background-color: #e2f0ff; }
}
EOF

# Создаем index.tsx
cat << 'EOF' > src/index.tsx
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement)
root.render(<App />)
EOF

# Создаем App.tsx
cat << 'EOF' > src/App.tsx
import React, { useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Home from './routes/Home'
import Layout from './routes/Layout'
import Offers from './routes/Offers'
import Sales from './routes/Sales'
import OfferDetails from './routes/OfferDetails'
import LoginRegister from './routes/LoginRegister'
import PersonalAccount from './routes/PersonalAccount'
import AdminPanel from './routes/AdminPanel'
import DoctorPanel from './routes/DoctorPanel'
import './App.css'

function App() {
  useEffect(() => {}, [])
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="service" element={<Offers />} />
          <Route path="service/:offerId" element={<OfferDetails />} />
          <Route path="sale" element={<Sales />} />
          <Route path="sale/:offerId" element={<OfferDetails />} />
          <Route path="auth" element={<LoginRegister />} />
          <Route path="personal/*" element={<PersonalAccount />} />
          <Route path="admin" element={<AdminPanel />} />
          <Route path="doctor" element={<DoctorPanel />} />
        </Route>
      </Routes>
    </Router>
  )
}

export default App
EOF

# Layout.tsx
cat << 'EOF' > src/routes/Layout.tsx
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
EOF

# Home.tsx
cat << 'EOF' > src/routes/Home.tsx
import React from 'react'
import { Box, Typography, Paper } from '@mui/material'

function Home() {
  return (
    <Box display="flex" flexDirection="column" gap={2}>
      <Paper sx={{ p: 4 }}>
        <Typography variant="h4" gutterBottom>Добро пожаловать!</Typography>
        <Typography>
          Наша клиника предлагает лучшие условия для лечения и профилактики.
        </Typography>
      </Paper>
      <Paper sx={{ p: 4 }}>
        <Typography variant="h5" gutterBottom>Преимущества</Typography>
        <Typography>
          Современное оборудование, опытные специалисты, комфорт и удобство.
        </Typography>
      </Paper>
    </Box>
  )
}

export default Home
EOF

# Offers.tsx
cat << 'EOF' > src/routes/Offers.tsx
import React, { useEffect, useState } from 'react'
import { Box, Card, CardActions, CardContent, Button, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getAllOffers } from '../services/api'
import { OfferItemListDTO } from '../services/types'

function Offers() {
  const [offers, setOffers] = useState<OfferItemListDTO[]>([])
  const navigate = useNavigate()
  useEffect(() => {
    getAllOffers().then(res => {
      setOffers(res.data)
    })
  }, [])
  return (
    <Box display="flex" flexWrap="wrap" gap={2}>
      {offers.map(o => (
        <Card key={o.offerId} sx={{ width: 250 }}>
          <CardContent>
            <Typography gutterBottom variant="h6">{o.name}</Typography>
          </CardContent>
          <CardActions>
            <Button onClick={() => navigate(\`/service/\${o.offerId}\`)}>Подробнее</Button>
          </CardActions>
        </Card>
      ))}
    </Box>
  )
}

export default Offers
EOF

# OfferDetails.tsx
cat << 'EOF' > src/routes/OfferDetails.tsx
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Box, Typography, Paper } from '@mui/material'
import { findOfferById } from '../services/api'
import { OfferDTO } from '../services/types'

function OfferDetails() {
  const { offerId } = useParams()
  const [offer, setOffer] = useState<OfferDTO | null>(null)
  useEffect(() => {
    if (offerId) {
      findOfferById(offerId).then(res => {
        setOffer(res.data)
      })
    }
  }, [offerId])
  return (
    <Box>
      {offer && (
        <Paper sx={{ p: 4 }}>
          <Typography variant="h5">{offer.name}</Typography>
          <Typography>Описание: {offer.description}</Typography>
          <Typography>Цена: {offer.price}</Typography>
          <Typography>Акция: {offer.isSale ? 'Да' : 'Нет'}</Typography>
        </Paper>
      )}
    </Box>
  )
}

export default OfferDetails
EOF

# Sales.tsx
cat << 'EOF' > src/routes/Sales.tsx
import React, { useEffect, useState } from 'react'
import { Box, Card, CardActions, CardContent, Button, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getAllSales } from '../services/api'
import { SalesItemListDTO } from '../services/types'

function Sales() {
  const [sales, setSales] = useState<SalesItemListDTO[]>([])
  const navigate = useNavigate()
  useEffect(() => {
    getAllSales().then(res => {
      setSales(res.data)
    })
  }, [])
  return (
    <Box display="flex" flexWrap="wrap" gap={2}>
      {sales.map(s => (
        <Card key={s.offerId} sx={{ width: 250 }}>
          <CardContent>
            <Typography gutterBottom variant="h6">{s.name}</Typography>
          </CardContent>
          <CardActions>
            <Button onClick={() => navigate(\`/sale/\${s.offerId}\`)}>Подробнее</Button>
          </CardActions>
        </Card>
      ))}
    </Box>
  )
}

export default Sales
EOF

# LoginRegister.tsx
cat << 'EOF' > src/routes/LoginRegister.tsx
import React, { useState } from 'react'
import { Box, TextField, Button, Paper } from '@mui/material'
import { loginPatient, registerPatient } from '../services/api'
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
EOF

# PersonalDrawer.tsx
cat << 'EOF' > src/components/PersonalDrawer.tsx
import React from 'react'
import { Drawer, List, ListItem, ListItemButton, ListItemText } from '@mui/material'

type Props = {
  open: boolean
  onClose: () => void
  onSelect: (route: string) => void
}

function PersonalDrawer({ open, onClose, onSelect }: Props) {
  return (
    <Drawer anchor="left" open={open} onClose={onClose}>
      <List>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('appointments')}>
            <ListItemText primary="Мои записи" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('tests')}>
            <ListItemText primary="Мои анализы" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('data')}>
            <ListItemText primary="Мои данные" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('prescriptions')}>
            <ListItemText primary="Рецепты" />
          </ListItemButton>
        </ListItem>
      </List>
    </Drawer>
  )
}

export default PersonalDrawer
EOF

# PersonalAccount.tsx
cat << 'EOF' > src/routes/PersonalAccount.tsx
import React, { useEffect, useState } from 'react'
import { Routes, Route, useNavigate } from 'react-router-dom'
import { Box, IconButton, AppBar, Toolbar, Typography } from '@mui/material'
import MenuIcon from '@mui/icons-material/Menu'
import PersonalDrawer from '../components/PersonalDrawer'
import Appointments from './Appointments'
import Tests from './Tests'
import AppointmentDetails from './AppointmentDetails'
import TestDetails from './TestDetails'
import PrescriptionList from './PrescriptionList'
import { getPatientData } from '../services/api'
import { PatientDTO } from '../services/types'

function PersonalAccount() {
  const navigate = useNavigate()
  const [open, setOpen] = useState(false)
  const [selected, setSelected] = useState('appointments')
  const [patient, setPatient] = useState<PatientDTO | null>(null)
  const patientData = localStorage.getItem('patient')

  useEffect(() => {
    if (!patientData) {
      navigate('/auth')
      return
    }
    const p = JSON.parse(patientData)
    if (p && p.id) {
      getPatientData(p.id).then(res => {
        setPatient(res.data)
      })
    }
  }, [navigate, patientData])

  const handleSelect = (route: string) => {
    setSelected(route)
    setOpen(false)
    navigate(`/personal/${route}`)
  }

  return (
    <Box>
      <AppBar position="static">
        <Toolbar>
          <IconButton color="inherit" onClick={() => setOpen(true)}>
            <MenuIcon />
          </IconButton>
          <Typography sx={{ flexGrow: 1 }}>Личный кабинет</Typography>
        </Toolbar>
      </AppBar>
      <PersonalDrawer open={open} onClose={() => setOpen(false)} onSelect={handleSelect} />
      <Routes>
        <Route path="appointments" element={<Appointments />} />
        <Route path="appointments/:id" element={<AppointmentDetails />} />
        <Route path="tests" element={<Tests />} />
        <Route path="tests/:id" element={<TestDetails />} />
        <Route path="data" element={
          <Box sx={{ p: 2 }}>
            {patient && (
              <Box>
                <Typography>Имя: {patient.name}</Typography>
                <Typography>Фамилия: {patient.surname}</Typography>
                <Typography>Отчество: {patient.patronymic}</Typography>
                <Typography>Дата рождения: {patient.dateOfBirth}</Typography>
                <Typography>Email: {patient.email}</Typography>
              </Box>
            )}
          </Box>
        } />
        <Route path="prescriptions" element={<PrescriptionList />} />
      </Routes>
    </Box>
  )
}

export default PersonalAccount
EOF

# Appointments.tsx
cat << 'EOF' > src/routes/Appointments.tsx
import React, { useEffect, useState } from 'react'
import { Box, Card, CardContent, Typography, CardActions, Button } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getCurrentAppointments, getExpiredAppointments, addAppointment } from '../services/api'
import { AppointmentItemListDTO, AppointmentAddDTO } from '../services/types'
import dayjs from 'dayjs'

function Appointments() {
  const patientStr = localStorage.getItem('patient')
  const [current, setCurrent] = useState<AppointmentItemListDTO[]>([])
  const [expired, setExpired] = useState<AppointmentItemListDTO[]>([])
  const [showAdd, setShowAdd] = useState(false)
  const navigate = useNavigate()
  useEffect(() => {
    if (patientStr) {
      const p = JSON.parse(patientStr)
      getCurrentAppointments(p.id).then(res => setCurrent(res.data))
      getExpiredAppointments(p.id).then(res => setExpired(res.data))
    }
  }, [patientStr])

  const handleCreateAppointment = async () => {
    if (patientStr) {
      const dto: AppointmentAddDTO = {
        doctorFullName: 'Иванов Иван Иванович',
        doctorPosition: 'Терапевт',
        dateTime: dayjs().add(1, 'day').toDate(),
        reason: 'Плановый осмотр'
      }
      await addAppointment(dto)
      alert('Запись создана статично (просто пример)')
    }
  }

  return (
    <Box sx={{ p: 2 }}>
      <Button variant="contained" onClick={() => {setShowAdd(true); handleCreateAppointment();}}>
        Записаться к врачу
      </Button>
      <Box display="flex" gap={2} flexWrap="wrap" mt={2}>
        {current.map(c => (
          <Card key={c.id} sx={{ width: 250 }}>
            <CardContent>
              <Typography>Дата: {dayjs(c.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
              <Typography>Причина: {c.reason}</Typography>
              <Typography>Статус: {c.status}</Typography>
            </CardContent>
            <CardActions>
              <Button onClick={() => navigate(`/personal/appointments/${c.id}`)}>
                Подробнее
              </Button>
            </CardActions>
          </Card>
        ))}
        {expired.map(e => (
          <Card key={e.id} sx={{ width: 250, opacity: 0.6 }}>
            <CardContent>
              <Typography>Дата: {dayjs(e.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
              <Typography>Причина: {e.reason}</Typography>
              <Typography>Статус: {e.status}</Typography>
            </CardContent>
            <CardActions>
              <Button onClick={() => navigate(`/personal/appointments/${e.id}`)}>
                Подробнее
              </Button>
            </CardActions>
          </Card>
        ))}
      </Box>
    </Box>
  )
}

export default Appointments
EOF

# AppointmentDetails.tsx
cat << 'EOF' > src/routes/AppointmentDetails.tsx
import React, { useEffect, useState } from 'react'
import { Box, Paper, Typography } from '@mui/material'
import { useParams } from 'react-router-dom'
import { getAppointmentById } from '../services/api'
import { AppointmentDTO } from '../services/types'
import dayjs from 'dayjs'

function AppointmentDetails() {
  const { id } = useParams()
  const [appointment, setAppointment] = useState<AppointmentDTO | null>(null)
  useEffect(() => {
    if (id) {
      getAppointmentById(id).then(res => setAppointment(res.data))
    }
  }, [id])
  return (
    <Box sx={{ p: 2 }}>
      {appointment && (
        <Paper sx={{ p: 2 }}>
          <Typography>Дата: {dayjs(appointment.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
          <Typography>Причина: {appointment.reason}</Typography>
          <Typography>Статус: {appointment.status}</Typography>
          <Typography>Врач: {appointment.doctorFullName} ({appointment.doctorPosition})</Typography>
          <Typography>Препараты: {appointment.drugs}</Typography>
          <Typography>Диагноз: {appointment.diagnose}</Typography>
          <Typography>Заметки врача: {appointment.doctorNotes}</Typography>
        </Paper>
      )}
    </Box>
  )
}

export default AppointmentDetails
EOF

# Tests.tsx
cat << 'EOF' > src/routes/Tests.tsx
import React, { useEffect, useState } from 'react'
import { Box, Card, CardContent, CardActions, Button, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getCurrentTests, getExpiredTests, addTest } from '../services/api'
import { TestsItemListDTO, TestAddDTO } from '../services/types'
import dayjs from 'dayjs'

function Tests() {
  const navigate = useNavigate()
  const patientStr = localStorage.getItem('patient')
  const [current, setCurrent] = useState<TestsItemListDTO[]>([])
  const [expired, setExpired] = useState<TestsItemListDTO[]>([])

  useEffect(() => {
    if (patientStr) {
      const p = JSON.parse(patientStr)
      getCurrentTests(p.id).then(res => setCurrent(res.data))
      getExpiredTests(p.id).then(res => setExpired(res.data))
    }
  }, [patientStr])

  const handleAddTest = async () => {
    if (patientStr) {
      const dto: TestAddDTO = {
        name: 'Общий анализ крови',
        dateTime: dayjs().add(2, 'day').toDate()
      }
      await addTest(dto)
      alert('Запись на анализ создана статично (пример)')
    }
  }

  return (
    <Box sx={{ p: 2 }}>
      <Button variant="contained" onClick={handleAddTest}>Записаться на анализ</Button>
      <Box display="flex" gap={2} flexWrap="wrap" mt={2}>
        {current.map(c => (
          <Card key={c.id} sx={{ width: 250 }}>
            <CardContent>
              <Typography>Анализ: {c.name}</Typography>
              <Typography>Дата: {dayjs(c.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
              <Typography>Статус: {c.status}</Typography>
            </CardContent>
            <CardActions>
              <Button onClick={() => navigate(`/personal/tests/${c.id}`)}>Подробнее</Button>
            </CardActions>
          </Card>
        ))}
        {expired.map(e => (
          <Card key={e.id} sx={{ width: 250, opacity: 0.6 }}>
            <CardContent>
              <Typography>Анализ: {e.name}</Typography>
              <Typography>Дата: {dayjs(e.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
              <Typography>Статус: {e.status}</Typography>
            </CardContent>
            <CardActions>
              <Button onClick={() => navigate(`/personal/tests/${e.id}`)}>Подробнее</Button>
            </CardActions>
          </Card>
        ))}
      </Box>
    </Box>
  )
}

export default Tests
EOF

# TestDetails.tsx
cat << 'EOF' > src/routes/TestDetails.tsx
import React, { useEffect, useState } from 'react'
import { Box, Paper, Typography } from '@mui/material'
import { useParams } from 'react-router-dom'
import { getTestById } from '../services/api'
import { TestDTO } from '../services/types'
import dayjs from 'dayjs'

function TestDetails() {
  const { id } = useParams()
  const [test, setTest] = useState<TestDTO | null>(null)
  useEffect(() => {
    if (id) {
      getTestById(id).then(res => setTest(res.data))
    }
  }, [id])
  return (
    <Box sx={{ p: 2 }}>
      {test && (
        <Paper sx={{ p: 2 }}>
          <Typography>Наименование анализа: {test.name}</Typography>
          <Typography>Дата: {dayjs(test.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
          <Typography>Статус: {test.status}</Typography>
          <Typography>Результат: {test.result}</Typography>
        </Paper>
      )}
    </Box>
  )
}

export default TestDetails
EOF

# PrescriptionList.tsx
cat << 'EOF' > src/routes/PrescriptionList.tsx
import React, { useEffect, useState } from 'react'
import { Box, Card, CardContent, Typography } from '@mui/material'
import { getPrescriptions } from '../services/api'
import { PrescriptionItemListDTO } from '../services/types'

function PrescriptionList() {
  const patientStr = localStorage.getItem('patient')
  const [list, setList] = useState<PrescriptionItemListDTO[]>([])
  useEffect(() => {
    if (patientStr) {
      const p = JSON.parse(patientStr)
      getPrescriptions(p.id).then(res => setList(res.data))
    }
  }, [patientStr])
  return (
    <Box display="flex" gap={2} flexWrap="wrap" sx={{ p: 2 }}>
      {list.map((item, index) => (
        <Card key={index} sx={{ width: 250 }}>
          <CardContent>
            <Typography>Название: {item.name}</Typography>
            <Typography>Выдано: {item.issuedAt}</Typography>
            <Typography>Истекает: {item.expiredAt}</Typography>
          </CardContent>
        </Card>
      ))}
    </Box>
  )
}

export default PrescriptionList
EOF

# AdminPanel.tsx
cat << 'EOF' > src/routes/AdminPanel.tsx
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
EOF

# DoctorPanel.tsx
cat << 'EOF' > src/routes/DoctorPanel.tsx
import React, { useState, useEffect } from 'react'
import { Box, Paper, TextField, Button, Typography, Card, CardContent, CardActions } from '@mui/material'
import { getDoctorAppointmentsToday, getDoctorAppointments, getDoctorPrescriptions } from '../services/api'
import { DoctorAppointmentDTO, DoctorPrescriptionDTO } from '../services/types'
import dayjs from 'dayjs'

function DoctorPanel() {
  const [doctorId, setDoctorId] = useState<number>(1)
  const [appointmentsToday, setAppointmentsToday] = useState<DoctorAppointmentDTO[]>([])
  const [appointments, setAppointments] = useState<DoctorAppointmentDTO[]>([])
  const [prescriptions, setPrescriptions] = useState<DoctorPrescriptionDTO[]>([])

  const fetchData = async () => {
    const res1 = await getDoctorAppointmentsToday(doctorId)
    setAppointmentsToday(res1.data)
    const res2 = await getDoctorAppointments(doctorId)
    setAppointments(res2.data)
    const res3 = await getDoctorPrescriptions(doctorId)
    setPrescriptions(res3.data)
  }

  useEffect(() => {
    fetchData()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return (
    <Box sx={{ p: 2 }} display="flex" flexDirection="column" gap={2}>
      <Paper sx={{ p: 2 }}>
        <Typography variant="h6">Доктор ID</Typography>
        <TextField type="number" value={doctorId} onChange={e => setDoctorId(Number(e.target.value))} />
        <Button variant="contained" onClick={fetchData} sx={{ ml: 2 }}>Загрузить</Button>
      </Paper>
      <Typography variant="h5">Записи на сегодня</Typography>
      <Box display="flex" gap={2} flexWrap="wrap">
        {appointmentsToday.map((a, i) => (
          <Card key={i} sx={{ width: 250 }}>
            <CardContent>
              <Typography>Пациент: {a.patientFullName}</Typography>
              <Typography>Дата: {dayjs(a.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
              <Typography>Причина: {a.reason}</Typography>
              <Typography>Статус: {a.status}</Typography>
              <Typography>Препараты: {a.drugs}</Typography>
              <Typography>Диагноз: {a.diagnose}</Typography>
              <Typography>Заметки: {a.doctorNotes}</Typography>
            </CardContent>
            <CardActions>
              <Button>Редактировать</Button>
            </CardActions>
          </Card>
        ))}
      </Box>
      <Typography variant="h5">Все записи</Typography>
      <Box display="flex" gap={2} flexWrap="wrap">
        {appointments.map((a, i) => (
          <Card key={i} sx={{ width: 250 }}>
            <CardContent>
              <Typography>Пациент: {a.patientFullName}</Typography>
              <Typography>Дата: {dayjs(a.dateTime).format('DD.MM.YYYY HH:mm')}</Typography>
              <Typography>Причина: {a.reason}</Typography>
              <Typography>Статус: {a.status}</Typography>
              <Typography>Препараты: {a.drugs}</Typography>
              <Typography>Диагноз: {a.diagnose}</Typography>
              <Typography>Заметки: {a.doctorNotes}</Typography>
            </CardContent>
            <CardActions>
              <Button>Редактировать</Button>
            </CardActions>
          </Card>
        ))}
      </Box>
      <Typography variant="h5">Мои рецепты</Typography>
      <Box display="flex" gap={2} flexWrap="wrap">
        {prescriptions.map((p, i) => (
          <Card key={i} sx={{ width: 250 }}>
            <CardContent>
              <Typography>Название: {p.name}</Typography>
              <Typography>Пациент: {p.patientFullName}</Typography>
              <Typography>Выдано: {p.issuedAt}</Typography>
              <Typography>Истекает: {p.expiredAt}</Typography>
            </CardContent>
          </Card>
        ))}
      </Box>
    </Box>
  )
}

export default DoctorPanel
EOF

# api.ts
cat << 'EOF' > src/services/api.ts
import axios from 'axios'
import { 
  LoginDTO, RegisterDTO, AppointmentAddDTO, 
  TestAddDTO 
} from './types'

const instance = axios.create({
  baseURL: 'http://localhost:8080'
})

export const getAllOffers = () => instance.get('/service')
export const findOfferById = (id: string) => instance.get(\`/service/\${id}\`)
export const getAllSales = () => instance.get('/sale')

export const loginPatient = (dto: LoginDTO) => instance.post('/auth/login', dto)
export const registerPatient = (dto: RegisterDTO) => instance.post('/auth/register', dto)
export const getPatientData = (id: number) => instance.get(\`/profile/\${id}\`)

export const getCurrentAppointments = (patientId: number) => instance.get(\`/appointment/current/\${patientId}\`)
export const getExpiredAppointments = (patientId: number) => instance.get(\`/appointment/expired/\${patientId}\`)
export const getAppointmentById = (appointmentId: string) => instance.get(\`/appointment/\${appointmentId}\`)
export const addAppointment = (dto: AppointmentAddDTO) => instance.post('/appointment', dto)

export const getCurrentTests = (patientId: number) => instance.get(\`/test/current/\${patientId}\`)
export const getExpiredTests = (patientId: number) => instance.get(\`/test/expired/\${patientId}\`)
export const getTestById = (testId: string) => instance.get(\`/test/\${testId}\`)
export const addTest = (dto: TestAddDTO) => instance.post('/test', dto)

export const getPrescriptions = (patientId: number) => instance.get(\`/prescription/\${patientId}\`)

export const getDoctorAppointmentsToday = (doctorId: number) => instance.get(\`/doctor/appointmentsToday/\${doctorId}\`)
export const getDoctorAppointments = (doctorId: number) => instance.get(\`/doctor/doctorAppointments/\${doctorId}\`)
export const getDoctorPrescriptions = (doctorId: number) => instance.get(\`/doctor/doctorPrescriptions/\${doctorId}\`)
EOF

# types.ts
cat << 'EOF' > src/services/types.ts
export interface OfferItemListDTO {
  offerId: number
  name: string
}

export interface OfferDTO {
  name: string
  description: string
  price: number
  isSale: boolean
}

export interface SalesItemListDTO {
  offerId: number
  name: string
}

export interface LoginDTO {
  email: string
  password: string
}

export interface RegisterDTO {
  name: string
  surname: string
  patronymic: string
  dateOfBirth: string
  email: string
  password: string
}

export interface PatientDTO {
  id?: number
  name: string
  surname: string
  patronymic: string
  dateOfBirth: string
  email: string
}

export interface AppointmentItemListDTO {
  id: number
  dateTime: string
  reason: string
  status: string
}

export interface AppointmentDTO {
  dateTime: string
  reason: string
  status: string
  doctorFullName: string
  doctorPosition: string
  drugs: string
  diagnose: string
  doctorNotes: string
}

export interface AppointmentAddDTO {
  doctorFullName: string
  doctorPosition: string
  dateTime: Date
  reason: string
}

export interface TestsItemListDTO {
  id: number
  name: string
  dateTime: string
  status: string
}

export interface TestDTO {
  name: string
  dateTime: string
  status: string
  result: string
}

export interface TestAddDTO {
  name: string
  dateTime: Date
}

export interface PrescriptionItemListDTO {
  name: string
  issuedAt: string
  expiredAt: string
}

export interface DoctorAppointmentDTO {
  patientFullName: string
  dateTime: string
  reason: string
  status: string
  drugs: string
  diagnose: string
  doctorNotes: string
}

export interface DoctorPrescriptionDTO {
  name: string
  patientFullName: string
  issuedAt: string
  expiredAt: string
}
EOF

chmod +x build.sh

echo "Проект успешно создан. Для сборки выполните:"
echo "1) cd medical-frontend"
echo "2) ./build.sh"
echo "После этого можно запустить development-сервер командой 'npm start' (или 'yarn start')."
echo "Либо раздать готовый бандл командой 'serve -s build' (npm i -g serve)."