import React from 'react'
import { Drawer, List, ListItem, ListItemButton, ListItemText } from '@mui/material'

type Props = {
  open: boolean
  onClose: () => void
  onSelect: (route: string) => void
}

function PersonalDrawer({ open, onClose, onSelect }: Props) {
  return (
    <Drawer anchor="left" open={open} onClose={onClose}>
      <List>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('appointments')}>
            <ListItemText primary="Мои записи" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('tests')}>
            <ListItemText primary="Мои анализы" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('data')}>
            <ListItemText primary="Мои данные" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton onClick={() => onSelect('prescriptions')}>
            <ListItemText primary="Рецепты" />
          </ListItemButton>
        </ListItem>
      </List>
    </Drawer>
  )
}

export default PersonalDrawer
