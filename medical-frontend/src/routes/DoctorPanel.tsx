import React, { useState, useEffect } from 'react'
import { Box, Paper, TextField, Button, Typography, Card, CardContent, CardActions } from '@mui/material'
import { getDoctorAppointmentsToday, getDoctorAppointments, getDoctorPrescriptions } from '../services/api.ts'
import { DoctorAppointmentDTO, DoctorPrescriptionDTO } from '../services/types.ts'
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
