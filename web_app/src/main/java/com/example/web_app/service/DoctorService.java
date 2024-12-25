package com.example.web_app.service;

import com.example.web_app.dto.DoctorAppointmentDTO;
import com.example.web_app.dto.DoctorPrescriptionAddDTO;
import com.example.web_app.dto.DoctorPrescriptionDTO;
import com.example.web_app.dto.UpdateAppointmentDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Patient;
import com.example.web_app.entity.Prescription;
import com.example.web_app.enums.Status;
import com.example.web_app.repositories.AppointmentsRepository;
import com.example.web_app.repositories.DoctorsRepository;
import com.example.web_app.repositories.PatientsRepository;
import com.example.web_app.repositories.PrescriptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorsRepository doctorsRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final PrescriptionsRepository prescriptionsRepository;
    private final PatientsRepository patientsRepository;


    private DoctorAppointmentDTO mapToDoctorAppointmentDTO(Appointment appointment){
        return DoctorAppointmentDTO.builder()
                .patientFullName(appointment.getPatient().getSurname() + " " + appointment.getPatient().getName() + " " + appointment.getPatient().getPatronymic())
                .dateTime(appointment.getDateTime())
                .status(appointment.getStatus().getContext())
                .reason(appointment.getReason().getContext())
                .diagnose(appointment.getDiagnose())
                .drugs(appointment.getDrugs())
                .doctorNotes(appointment.getDoctorNotes())
                .build();
    }

    private Prescription mapDoctorPrescriptionDTOToEntity(DoctorPrescriptionAddDTO doctorPrescriptionAddDTO){
        String[] patientFullNameArray = doctorPrescriptionAddDTO.getPatientFullName().split(" ");
        String[] doctorFullNameArray = doctorPrescriptionAddDTO.getDoctorFullName().split(" ");
        Patient patient = patientsRepository
                .findByNameAndSurnameAndPatronymic(
                        patientFullNameArray[0],
                        patientFullNameArray[1],
                        patientFullNameArray[2]
                ).orElseThrow(EntityNotFoundException::new);
        Doctor doctor = doctorsRepository
                .findByNameAndSurnameAndPatronymicAndPosition(
                        doctorFullNameArray[0],
                        doctorFullNameArray[1],
                        doctorFullNameArray[2],
                        doctorFullNameArray[3]
                ).orElseThrow(EntityNotFoundException::new);

        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setName(doctorPrescriptionAddDTO.getName());
        prescription.setIssuedAt(doctorPrescriptionAddDTO.getIssuedAt());
        prescription.setExpiredAt(doctorPrescriptionAddDTO.getExpiredAt());
        return prescription;
    }

    private DoctorPrescriptionDTO mapToDoctorPrescriptionDTO(Prescription prescription){
        return DoctorPrescriptionDTO.builder()
                .name(prescription.getName())
                .patientFullName(prescription.getPatient().getSurname() + " " + prescription.getPatient().getName() + " " + prescription.getPatient().getPatronymic())
                .issuedAt(prescription.getIssuedAt())
                .expiredAt(prescription.getExpiredAt())
                .build();
    }

    public List<DoctorAppointmentDTO> getDoctorAppointments(Long doctorId) {
        return appointmentsRepository.findAllByDoctorId(doctorId).stream()
                .map(this::mapToDoctorAppointmentDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorPrescriptionDTO> getDoctorPrescriptions(Long doctorId) {
        return prescriptionsRepository.findAllByDoctorId(doctorId).stream()
                .map(this::mapToDoctorPrescriptionDTO)
                .collect(Collectors.toList());
    }


    public List<DoctorAppointmentDTO> getDoctorAppointmentsToday(Long doctorId) {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay(); // Начало сегодняшнего дня
        LocalDateTime endOfDay = today.atTime(23, 59, 59); // Конец сегодняшнего дня

        return appointmentsRepository.findAllByDoctorId(doctorId).stream()
                .filter(appointment ->
                        appointment.getDateTime().isAfter(startOfDay) &&
                                appointment.getDateTime().isBefore(endOfDay) &&
                                Objects.equals(appointment.getStatus().getContext(), "scheduled")
                )
                .map(this::mapToDoctorAppointmentDTO)
                .collect(Collectors.toList());
    }

    public Appointment updateTodayAppointment(String name, String surname, String patronymic, Long doctorId, UpdateAppointmentDTO dto) {
        // Находим пациента по ФИО
        Patient patient = patientsRepository.findByNameAndSurnameAndPatronymic(name, surname, patronymic)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        // Определяем диапазон текущего дня
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        // Ищем запись за сегодняшний день для пациента и врача
        Appointment appointment = appointmentsRepository.findTodayAppointmentByPatientAndDoctor(patient, doctorId, startOfDay, endOfDay)
                .orElseThrow(() -> new IllegalArgumentException("No appointments found for today with the given doctor"));

        // Обновляем поля записи
        if (dto.getDiagnose() != null) {
            appointment.setDiagnose(dto.getDiagnose());
        }
        if (dto.getDoctorNotes() != null) {
            appointment.setDoctorNotes(dto.getDoctorNotes());
        }
        if (dto.getDrugs() != null) {
            appointment.setDrugs(dto.getDrugs());
        }
        if (dto.getStatus() != null) {
            appointment.setStatus(Status.valueOf(dto.getStatus())); // Преобразуем строку в enum
        }

        // Сохраняем обновленную запись
        return appointmentsRepository.save(appointment);
    }


    public void addPrescription(DoctorPrescriptionAddDTO doctorPrescriptionAddDTO) {
        prescriptionsRepository.save(mapDoctorPrescriptionDTOToEntity(doctorPrescriptionAddDTO));
    }
}
