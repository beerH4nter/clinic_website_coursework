package com.example.web_app.mapper;

import com.example.web_app.dto.PatientDTO;
import com.example.web_app.entity.Patient;
import com.example.web_app.entity.Test;
import com.example.web_app.repositories.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    private final TestsRepository testsRepository;

    @Autowired
    public PatientMapper(TestsRepository testsRepository) {
        this.testsRepository = testsRepository;
    }

    public Patient toRequest(PatientDTO patientDTO) {

//        List<Test> tests = testsRepository.findAllById(patientDTO.getTestsId());

        Patient patient = new Patient();

        patient.setName(patientDTO.getName());
        patient.setSurname(patientDTO.getSurname());
        patient.setPatronymic(patientDTO.getPatronymic());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setPassword(patientDTO.getPassword());
//        patient.setTests(tests);
        return patient;

    }

    public PatientDTO toResponse(Patient patient){

        PatientDTO patientDTO = new PatientDTO();


        patientDTO.setName(patient.getName());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setPatronymic(patient.getPatronymic());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setPassword(patient.getPassword());
        patientDTO.setTestsId(patient.getTests().stream().map(Test::getId).collect(Collectors.toList()));

        return patientDTO;

    }

}
