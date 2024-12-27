import React, { useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Home from './routes/Home.tsx'
import Layout from './routes/Layout.tsx'
import Offers from './routes/Offers.tsx'
import Sales from './routes/Sales.tsx'
import OfferDetails from './routes/OfferDetails.tsx'
import LoginRegister from './routes/LoginRegister.tsx'
import PersonalAccount from './routes/PersonalAccount.tsx'
import AdminPanel from './routes/AdminPanel.tsx'
import DoctorPanel from './routes/DoctorPanel.tsx'
import './App.css'

function App() {
  useEffect(() => {}, [])
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="service" element={<Offers />} />
          <Route path="service/:offerId" element={<OfferDetails />} />
          <Route path="sale" element={<Sales />} />
          <Route path="sale/:offerId" element={<OfferDetails />} />
          <Route path="auth" element={<LoginRegister />} />
          <Route path="personal/*" element={<PersonalAccount />} />
          <Route path="admin" element={<AdminPanel />} />
          <Route path="doctor" element={<DoctorPanel />} />
        </Route>
      </Routes>
    </Router>
  )
}

export default App
