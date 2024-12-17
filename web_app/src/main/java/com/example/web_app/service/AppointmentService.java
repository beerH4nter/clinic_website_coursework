package com.example.web_app.service;


import com.example.web_app.dto.AppointmentItemListDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.repositories.AppointmentsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentsRepository repository;

    private AppointmentItemListDTO mapToAppointmentDTO(Appointment appointment){
        return AppointmentItemListDTO.builder()
                .id(appointment.getId())
                .dateTime(appointment.getDateTime())
                .reason(appointment.getReason().getContext())
                .status(appointment.getStatus().getContext())
                .build();
    }


    public List<AppointmentItemListDTO> getAllByPatient(Long id) {
        return repository.findAllByPatientId(id).stream()
                .map(this::mapToAppointmentDTO)
                .collect(Collectors.toList());
    }
}
