import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Box, Typography, Paper } from '@mui/material'
import { findOfferById } from '../services/api.ts'
import { OfferDTO } from '../services/types.ts'

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
