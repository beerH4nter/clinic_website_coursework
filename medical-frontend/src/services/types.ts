export interface OfferItemListDTO {
  offerId: number
  name: string
}

export interface OfferDTO {
  name: string
  description: string
  price: number
  isSale: boolean
}

export interface SalesItemListDTO {
  offerId: number
  name: string
}

export interface LoginDTO {
  email: string
  password: string
}

export interface RegisterDTO {
  name: string
  surname: string
  patronymic: string
  dateOfBirth: string
  email: string
  password: string
}

export interface PatientDTO {
  id?: number
  name: string
  surname: string
  patronymic: string
  dateOfBirth: string
  email: string
}

export interface AppointmentItemListDTO {
  id: number
  dateTime: string
  reason: string
  status: string
}

export interface AppointmentDTO {
  dateTime: string
  reason: string
  status: string
  doctorFullName: string
  doctorPosition: string
  drugs: string
  diagnose: string
  doctorNotes: string
}

export interface AppointmentAddDTO {
  doctorFullName: string
  doctorPosition: string
  dateTime: Date
  reason: string
}

export interface TestsItemListDTO {
  id: number
  name: string
  dateTime: string
  status: string
}

export interface TestDTO {
  name: string
  dateTime: string
  status: string
  result: string
}

export interface TestAddDTO {
  name: string
  dateTime: Date
}

export interface PrescriptionItemListDTO {
  name: string
  issuedAt: string
  expiredAt: string
}

export interface DoctorAppointmentDTO {
  patientFullName: string
  dateTime: string
  reason: string
  status: string
  drugs: string
  diagnose: string
  doctorNotes: string
}

export interface DoctorPrescriptionDTO {
  name: string
  patientFullName: string
  issuedAt: string
  expiredAt: string
}
