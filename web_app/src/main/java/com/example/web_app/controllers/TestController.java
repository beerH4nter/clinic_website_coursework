package com.example.web_app.controllers;

import com.example.web_app.dto.TestDTO;
import com.example.web_app.entity.Test;
import com.example.web_app.mapper.TestMapper;
import com.example.web_app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/save")
    public Test saveTest(@RequestBody @Valid TestDTO testDTO){
        Test test = TestMapper.toRequest(testDTO);
        testService.save(test);
        return test;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTests(){
        try{
            List<Test> tests = testService.findAll();
            return ResponseEntity.ok(tests);
        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with tests data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("findAllByPatient")
    public ResponseEntity<?> getAllTestsByPatientId(int id){
        try{
            List<Test> tests = testService.findAllTestsByPatientId(id);
            return ResponseEntity.ok(tests);
        }catch(ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with tests data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }

    }

}
