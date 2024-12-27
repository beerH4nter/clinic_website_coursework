import React, { useEffect, useState } from 'react'
import { Routes, Route, useNavigate } from 'react-router-dom'
import { Box, IconButton, AppBar, Toolbar, Typography } from '@mui/material'
import MenuIcon from '@mui/icons-material/Menu'
import PersonalDrawer from '../components/PersonalDrawer.tsx'
import Appointments from './Appointments.tsx'
import Tests from './Tests.tsx'
import AppointmentDetails from './AppointmentDetails.tsx'
import TestDetails from './TestDetails.tsx'
import PrescriptionList from './PrescriptionList.tsx'
import { getPatientData } from '../services/api.ts'
import { PatientDTO } from '../services/types.ts'

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
