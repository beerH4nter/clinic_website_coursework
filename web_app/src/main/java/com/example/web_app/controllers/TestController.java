package com.example.web_app.controllers;

import com.example.web_app.dto.DoctorDTO;
import com.example.web_app.dto.TestDTO;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Test;
import com.example.web_app.mapper.TestMapper;
import com.example.web_app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TestService testService;
    private final TestMapper testMapper;

    @Autowired
    public TestController(TestService testService, TestMapper testMapper) {
        this.testService = testService;
        this.testMapper = testMapper;
    }

    @PostMapping("/save")
    public Test saveTest(@RequestBody @Valid TestDTO testDTO){
        Test test = testMapper.toRequest(testDTO);
        testService.save(test);
        return test;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTests(){
        try{
            List<Test> tests = testService.findAll();

            List<TestDTO> testDTOS = tests.stream()
                    .map(testMapper::toResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(testDTOS);
        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with tests data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("findByPatient")
    public ResponseEntity<?> getAllTestsByPatientId(int id){
        try{
            List<Test> tests = testService.findAllTestsByPatientId(id);

            List<TestDTO> testDTOS = tests.stream()
                    .map(testMapper::toResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(testDTOS);
        }catch(ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with tests data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }

    }

}
