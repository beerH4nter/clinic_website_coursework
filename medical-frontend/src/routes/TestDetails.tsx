import React, { useEffect, useState } from 'react'
import { Box, Paper, Typography } from '@mui/material'
import { useParams } from 'react-router-dom'
import { getTestById } from '../services/api.ts'
import { TestDTO } from '../services/types.ts'
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
