package com.example.web_app.controllers;

import com.example.web_app.dto.PatientDTO;
import com.example.web_app.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class PersonalAccountController {
    private final PatientService service;

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

}
