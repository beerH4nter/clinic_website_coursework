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
