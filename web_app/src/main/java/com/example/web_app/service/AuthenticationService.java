package com.example.web_app.service;


import com.example.web_app.dto.LoginDTO;
import com.example.web_app.dto.PatientDTO;
import com.example.web_app.dto.RegisterDTO;
import com.example.web_app.entity.Patient;
import com.example.web_app.repositories.PatientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PatientsRepository repository;

    private Patient mapRegisterDTOToEntity(RegisterDTO registerDTO){
        Patient patient = new Patient();
        patient.setName(registerDTO.getName());
        patient.setSurname(registerDTO.getSurname());
        patient.setPatronymic(registerDTO.getPatronymic());
        patient.setDateOfBirth(registerDTO.getDateOfBirth());
        patient.setEmail(registerDTO.getEmail());
        patient.setPassword(registerDTO.getPassword());
        return patient;
    }

    public Optional<Patient> login(LoginDTO loginDTO) {
        // Находим пользователя по email
        return repository.findByEmail(loginDTO.getEmail())
                .filter(patient -> patient.getPassword().equals(loginDTO.getPassword())); // Проверяем пароль
    }

    public Patient register(RegisterDTO registerDTO) {
        // Проверяем, существует ли пользователь с таким email
        if (repository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exist");
        }

        Patient patient = mapRegisterDTOToEntity(registerDTO);
        System.out.println(patient.toString());
        return repository.save(patient); // Сохраняем пациента
    }

}
