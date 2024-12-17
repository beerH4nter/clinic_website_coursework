package com.example.web_app.controllers;

import com.example.web_app.dto.AppointmentItemListDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping("/current/{patientId}")
    public ResponseEntity<List<AppointmentItemListDTO>> getAllByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(service.getAllByPatient(patientId));
    }



}
