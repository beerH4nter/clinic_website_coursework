import React, { useEffect, useState } from 'react'
import { Box, Card, CardContent, Typography, CardActions, Button } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getCurrentAppointments, getExpiredAppointments, addAppointment } from '../services/api.ts'
import { AppointmentItemListDTO, AppointmentAddDTO } from '../services/types.ts'
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
