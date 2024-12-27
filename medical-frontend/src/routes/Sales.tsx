import React, { useEffect, useState } from 'react'
import { Box, Card, CardActions, CardContent, Button, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getAllSales } from '../services/api.ts'
import { SalesItemListDTO } from '../services/types.ts'

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
            <Button onClick={() => navigate(`/sale/${s.offerId}`)}>Подробнее</Button>
          </CardActions>
        </Card>
      ))}
    </Box>
  )
}

export default Sales
