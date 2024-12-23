package com.example.web_app.service;


import com.example.web_app.dto.AppointmentAddDTO;
import com.example.web_app.dto.AppointmentDTO;
import com.example.web_app.dto.AppointmentItemListDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.enums.Reason;
import com.example.web_app.enums.Status;
import com.example.web_app.repositories.AppointmentsRepository;
import com.example.web_app.repositories.DoctorsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentsRepository repository;
    private final DoctorsRepository doctorsRepository;

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

    public Appointment mapAppointmentAddDTOToEntity(AppointmentAddDTO appointmentAddDTO){
        Appointment appointment = new Appointment();
        String[] doctorFullNameArray = appointmentAddDTO.getDoctorFullName().split(" ");
        appointment.setDateTime(appointmentAddDTO.getDateTime());
        appointment.setDoctor(doctorsRepository
                .findByFirstNameAndLastNameAndMiddleNameAndPosition(
                        doctorFullNameArray[0],
                        doctorFullNameArray[1],
                        doctorFullNameArray[2],
                        appointmentAddDTO.getDoctorPosition()).orElseThrow(EntityNotFoundException::new)
                );
        appointment.setReason(Reason.fromValue(appointmentAddDTO.getReason()));
        appointment.setStatus(Status.SCHEDULED);
        appointment.setDiagnose(null);
        appointment.setDrugs(null);
        appointment.setDoctorNotes(null);
        return appointment;
    }

    public List<AppointmentItemListDTO> getAllCurrentByPatient(Long patientId) {
        return repository.findAllByPatientId(patientId).stream()
                .filter(x -> Objects.equals(x.getStatus().getContext(), "scheduled"))
                .map(this::mapToAppointmentItemListDTO)
                .collect(Collectors.toList());
    }


    public List<AppointmentItemListDTO> getAllExpiredByPatient(Long patientId) {
        return repository.findAllByPatientId(patientId).stream()
                .filter(x -> Objects.equals(x.getStatus().getContext(), "canceled") || Objects.equals(x.getStatus().getContext(), "completed"))
                .map(this::mapToAppointmentItemListDTO)
                .collect(Collectors.toList());
    }


    public AppointmentDTO getById(Long id) {
        return mapToAppointmentDTO(repository.getById(id));
    }

    public void add(AppointmentAddDTO appointmentAddDTO) {
        repository.save(mapAppointmentAddDTOToEntity(appointmentAddDTO));
    }
}
