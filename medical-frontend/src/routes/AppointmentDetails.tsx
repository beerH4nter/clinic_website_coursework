import React, { useEffect, useState } from 'react'
import { Box, Paper, Typography } from '@mui/material'
import { useParams } from 'react-router-dom'
import { getAppointmentById } from '../services/api.ts'
import { AppointmentDTO } from '../services/types.ts'
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
