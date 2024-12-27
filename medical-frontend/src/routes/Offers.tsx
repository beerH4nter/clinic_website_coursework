import React, { useEffect, useState } from 'react'
import { Box, Card, CardActions, CardContent, Button, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import { getAllOffers } from '../services/api.ts'
import { OfferItemListDTO } from '../services/types.ts'

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
            <Button onClick={() => navigate(`/service/${o.offerId}`)}>Подробнее</Button>
          </CardActions>
        </Card>
      ))}
    </Box>
  )
}

export default Offers
