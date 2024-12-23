package com.example.web_app.service;

import com.example.web_app.dto.PatientDTO;
import com.example.web_app.entity.Patient;
import com.example.web_app.repositories.PatientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientsRepository repository;

    private PatientDTO mapToPatientDTO(Patient patient){
        return PatientDTO.builder()
                .name(patient.getName())
                .surname(patient.getSurname())
                .patronymic(patient.getPatronymic())
                .dateOfBirth(patient.getDateOfBirth())
                .email(patient.getEmail())
                .build();
    }

    public PatientDTO getById(Long id) {
        return mapToPatientDTO(repository.getById(id));
    }
}
