import axios from 'axios'
import { 
  LoginDTO, RegisterDTO, AppointmentAddDTO, 
  TestAddDTO 
} from './types.ts'

const instance = axios.create({
  baseURL: 'http://localhost:8080'
})

export const getAllOffers = () => instance.get('/service')
export const findOfferById = (id: string) => instance.get(`/service/${id}`)
export const getAllSales = () => instance.get('/sale')

export const loginPatient = (dto: LoginDTO) => instance.post('/auth/login', dto)
export const registerPatient = (dto: RegisterDTO) => instance.post('/auth/register', dto)
export const getPatientData = (id: number) => instance.get(`/profile/${id}`)

export const getCurrentAppointments = (patientId: number) => instance.get(`/appointment/current/${patientId}`)
export const getExpiredAppointments = (patientId: number) => instance.get(`/appointment/expired/${patientId}`)
export const getAppointmentById = (appointmentId: string) => instance.get(`/appointment/${appointmentId}`)
export const addAppointment = (dto: AppointmentAddDTO) => instance.post('/appointment', dto)

export const getCurrentTests = (patientId: number) => instance.get(`/test/current/${patientId}`)
export const getExpiredTests = (patientId: number) => instance.get(`/test/expired/${patientId}`)
export const getTestById = (testId: string) => instance.get(`/test/${testId}`)
export const addTest = (dto: TestAddDTO) => instance.post('/test', dto)

export const getPrescriptions = (patientId: number) => instance.get(`/prescription/${patientId}`)

export const getDoctorAppointmentsToday = (doctorId: number) => instance.get(`/doctor/appointmentsToday/${doctorId}`)
export const getDoctorAppointments = (doctorId: number) => instance.get(`/doctor/doctorAppointments/${doctorId}`)
export const getDoctorPrescriptions = (doctorId: number) => instance.get(`/doctor/doctorPrescriptions/${doctorId}`)
