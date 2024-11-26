package com.example.web_app.mapper;


import com.example.web_app.dto.TestDTO;
import com.example.web_app.entity.Patient;
import com.example.web_app.entity.Test;
import com.example.web_app.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    private final PatientsRepository patientsRepository;

    @Autowired
    TestMapper(PatientsRepository patientsRepository){
        this.patientsRepository = patientsRepository;
    }

    public Test toRequest(TestDTO testDTO) {

        Patient patient = patientsRepository.findById(testDTO.getPatientId()).orElseThrow(() ->
                new RuntimeException("Patient with ID " + testDTO.getPatientId() + " not found"));


        Test test = new Test();
        test.setName(testDTO.getName());
        test.setDate(testDTO.getDate());
        test.setTime(testDTO.getTime());
        test.setStatus(testDTO.getStatus());
        test.setResult(testDTO.getResult());
        test.setPatient(patient);
        return test;

    }

    public TestDTO toResponse(Test test){
        TestDTO response = new TestDTO();
        response.setName(test.getName());
        response.setDate(test.getDate());
        response.setTime(test.getTime());
        response.setStatus(test.getStatus());
        response.setResult(test.getResult());
        response.setPatientSurname(test.getPatient().getSurname());
        return response;

    }

}
