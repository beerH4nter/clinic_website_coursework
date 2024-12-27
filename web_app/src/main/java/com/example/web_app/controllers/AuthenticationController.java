package com.example.web_app.controllers;

import com.example.web_app.dto.LoginDTO;
import com.example.web_app.dto.RegisterDTO;
import com.example.web_app.entity.Patient;
import com.example.web_app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    // Логин
    @PostMapping("/login")
    public ResponseEntity<Patient> login(@RequestBody @Valid LoginDTO loginDTO) {
        Patient patient = service.login(loginDTO).orElseThrow(() ->
                new IllegalArgumentException("Login data is wrong")
        );
        return ResponseEntity.ok(patient);
    }

    // Регистрация
    @PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody @Valid RegisterDTO registerDTO) {
        Patient patient = service.register(registerDTO);
        return ResponseEntity.ok(patient);
    }



}
