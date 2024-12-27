package com.example.web_app.service;


import com.example.web_app.dto.AppointmentAddDTO;
import com.example.web_app.dto.AppointmentDTO;
import com.example.web_app.dto.AppointmentItemListDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.entity.Patient;
import com.example.web_app.enums.Reason;
import com.example.web_app.enums.Status;
import com.example.web_app.repositories.AppointmentsRepository;
import com.example.web_app.repositories.DoctorsRepository;
import com.example.web_app.repositories.PatientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentsRepository appointmentsRepository;
    private final DoctorsRepository doctorsRepository;
    private final PatientsRepository patientsRepository;

    private AppointmentItemListDTO mapToAppointmentItemListDTO(Appointment appointment){
        return AppointmentItemListDTO.builder()
                .id(appointment.getId())
                .dateTime(appointment.getDateTime())
                .reason(appointment.getReason().getContext())
                .status(appointment.getStatus().getContext())
                .build();
    }

    private AppointmentDTO mapToAppointmentDTO(Appointment appointment){
        return AppointmentDTO.builder()
                .dateTime(appointment.getDateTime())
                .reason(appointment.getReason().getContext())
                .status(appointment.getStatus().getContext())
                .doctorPosition(appointment.getDoctor().getPosition())
                .doctorFullName(appointment.getDoctor().getSurname() + " " + appointment.getDoctor().getName() + " " + appointment.getDoctor().getPatronymic())
                .drugs(appointment.getDrugs())
                .diagnose(appointment.getDiagnose())
                .doctorNotes(appointment.getDiagnose())
                .build();
    }

    private Appointment mapAppointmentAddDTOToEntity(AppointmentAddDTO appointmentAddDTO){
        Appointment appointment = new Appointment();
        Patient patient = patientsRepository.getById(appointmentAddDTO.getPatientId());
        String[] doctorFullNameArray = appointmentAddDTO.getDoctorFullName().split(" ");
        appointment.setDateTime(appointmentAddDTO.getDateTime());
        appointment.setDoctor(doctorsRepository
                .findByNameAndSurnameAndPatronymicAndPosition(
                        doctorFullNameArray[0],
                        doctorFullNameArray[1],
                        doctorFullNameArray[2],
                        appointmentAddDTO.getDoctorPosition()).orElseThrow(EntityNotFoundException::new)
                );
        appointment.setPatient(patient);
        appointment.setReason(Reason.fromValue(appointmentAddDTO.getReason()));
        appointment.setStatus(Status.SCHEDULED);
        appointment.setDiagnose(null);

        appointment.setDrugs(null);
        appointment.setDoctorNotes(null);
        return appointment;
    }

    public List<AppointmentItemListDTO> getAllCurrentByPatient(Long patientId) {
        return appointmentsRepository.findAllByPatientId(patientId).stream()
                .filter(x -> Objects.equals(x.getStatus().getContext(), "scheduled"))
                .map(this::mapToAppointmentItemListDTO)
                .collect(Collectors.toList());
    }


    public List<AppointmentItemListDTO> getAllExpiredByPatient(Long patientId) {
        return appointmentsRepository.findAllByPatientId(patientId).stream()
                .filter(x -> Objects.equals(x.getStatus().getContext(), "canceled") || Objects.equals(x.getStatus().getContext(), "completed"))
                .map(this::mapToAppointmentItemListDTO)
                .collect(Collectors.toList());
    }


    public AppointmentDTO getById(Long id) {
        return mapToAppointmentDTO(appointmentsRepository.getById(id));
    }

    public void add(AppointmentAddDTO appointmentAddDTO) {
        appointmentsRepository.save(mapAppointmentAddDTOToEntity(appointmentAddDTO));
    }

    public void delete(Long id){
        appointmentsRepository.deleteById(id);
    }
}
