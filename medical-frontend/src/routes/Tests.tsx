import React, { useEffect, useState } from 'react'
import { Box, Card, CardContent, CardActions, Button, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getCurrentTests, getExpiredTests, addTest } from '../services/api.ts'
import { TestsItemListDTO, TestAddDTO } from '../services/types.ts'
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
