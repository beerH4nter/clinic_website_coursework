package com.example.web_app.service;


import com.example.web_app.dto.*;
import com.example.web_app.entity.Appointment;
import com.example.web_app.entity.Test;
import com.example.web_app.repositories.TestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestsRepository repository;

    private TestsItemListDTO mapToTestsItemListDTO(Test test){
        return TestsItemListDTO.builder()
                .id(test.getId())
                .name(test.getName())
                .dateTime(test.getDateTime())
                .status(test.getStatus().getContext())
                .build();
    }

    private TestDTO mapToTestDTO(Test test){
        return TestDTO.builder()
                .name(test.getName())
                .dateTime(test.getDateTime())
                .status(test.getStatus().getContext())
                .result(test.getResult())
                .build();
    }

    private Test mapTestAddDTOToEntity(TestAddDTO testAddDTO){
        Test test = new Test();
        test.setName(testAddDTO.getName());
        test.setDateTime(testAddDTO.getDateTime());
        return test;
    }

    public List<TestsItemListDTO> getAllCurrentByPatient(Long patientId) {
        return repository.findAllByPatientId(patientId).stream()
                .filter(x -> Objects.equals(x.getStatus().getContext(), "scheduled")|| Objects.equals(x.getStatus().getContext(), "in progress"))
                .map(this::mapToTestsItemListDTO)
                .collect(Collectors.toList());
    }

    public List<TestsItemListDTO> getAllExpiredByPatient(Long patientId) {
        return repository.findAllByPatientId(patientId).stream()
                .filter(x -> Objects.equals(x.getStatus().getContext(), "canceled")|| Objects.equals(x.getStatus().getContext(), "completed"))
                .map(this::mapToTestsItemListDTO)
                .collect(Collectors.toList());
    }


    public TestDTO getById(Long testId) {
        return mapToTestDTO(repository.getById(testId));
    }

    public void add(TestAddDTO testAddDTO) {
        repository.save(mapTestAddDTOToEntity(testAddDTO));
    }
}
