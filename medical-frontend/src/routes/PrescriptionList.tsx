import React, { useEffect, useState } from 'react'
import { Box, Card, CardContent, Typography } from '@mui/material'
import { getPrescriptions } from '../services/api.ts'
import { PrescriptionItemListDTO } from '../services/types.ts'

function PrescriptionList() {
  const patientStr = localStorage.getItem('patient')
  const [list, setList] = useState<PrescriptionItemListDTO[]>([])
  useEffect(() => {
    if (patientStr) {
      const p = JSON.parse(patientStr)
      getPrescriptions(p.id).then(res => setList(res.data))
    }
  }, [patientStr])
  return (
    <Box display="flex" gap={2} flexWrap="wrap" sx={{ p: 2 }}>
      {list.map((item, index) => (
        <Card key={index} sx={{ width: 250 }}>
          <CardContent>
            <Typography>Название: {item.name}</Typography>
            <Typography>Выдано: {item.issuedAt}</Typography>
            <Typography>Истекает: {item.expiredAt}</Typography>
          </CardContent>
        </Card>
      ))}
    </Box>
  )
}

export default PrescriptionList
