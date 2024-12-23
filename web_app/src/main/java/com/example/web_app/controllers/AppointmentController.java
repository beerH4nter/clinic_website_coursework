package com.example.web_app.controllers;

import com.example.web_app.dto.AppointmentAddDTO;
import com.example.web_app.dto.AppointmentDTO;
import com.example.web_app.dto.AppointmentItemListDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping("/current/{patientId}")
    public ResponseEntity<List<AppointmentItemListDTO>> getAllCurrentByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(service.getAllCurrentByPatient(patientId));
    }

    @GetMapping("/expired/{patientId}")
    public ResponseEntity<List<AppointmentItemListDTO>> getAllExpiredByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(service.getAllExpiredByPatient(patientId));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable Long appointmentId){
        return ResponseEntity.ok(service.getById(appointmentId));
    }

    @PostMapping
    public void addAppointment(@RequestBody @Valid AppointmentAddDTO appointmentAddDTO){
        service.add(appointmentAddDTO);
    }


}
