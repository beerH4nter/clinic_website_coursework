package com.example.web_app.controllers;

import com.example.web_app.dto.PrescriptionItemListDTO;
import com.example.web_app.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prescription")
@RequiredArgsConstructor
public class PrescriptionsController {

    private final PrescriptionService service;

    @GetMapping("/{patientId}")
    public ResponseEntity<List<PrescriptionItemListDTO>> getAllByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(service.getAllByPatientId(patientId));
    }
}
