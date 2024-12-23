package com.example.web_app.controllers;


import com.example.web_app.dto.*;
import com.example.web_app.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService service;


    @GetMapping("/current/{patientId}")
    public ResponseEntity<List<TestsItemListDTO>> getAllCurrentByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(service.getAllCurrentByPatient(patientId));
    }

    @GetMapping("/expired/{patientId}")
    public ResponseEntity<List<TestsItemListDTO>> getAllExpiredByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(service.getAllExpiredByPatient(patientId));
    }


    @GetMapping("/{testId}")
    public ResponseEntity<TestDTO> getById(@PathVariable Long testId){
        return ResponseEntity.ok(service.getById(testId));
    }


    @PostMapping
    public void addTest(@RequestBody @Valid TestAddDTO testAddDTO){
        service.add(testAddDTO);
    }

}
