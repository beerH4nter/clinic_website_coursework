package com.example.web_app.controllers;

import com.example.web_app.dto.PatientDTO;
import com.example.web_app.entity.Patient;
import com.example.web_app.mapper.PatientMapper;
import com.example.web_app.service.PatientService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    private final PatientMapper  patientMapper;

    @Autowired
    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping("/save")
    public Patient savePatient(@RequestBody @Valid PatientDTO patientDTO){

        Patient patient = patientMapper.toRequest(patientDTO);

        patientService.save(patient);

        return patient;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPatients(){
        try{

            List<Patient> patients = patientService.findAll();
            List<PatientDTO> patientDTOS = patients.stream()
                    .map(patientMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());

            return ResponseEntity.ok(patientDTOS);
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with patients data");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }

    }

    @GetMapping("/findById")
    public ResponseEntity<?> findOneById(@RequestParam int id){
        try{
            Patient patient = patientService.findOneById(id);
            PatientDTO patientDTO = patientMapper.toResponse(patient);
            return ResponseEntity.ok(patientDTO);
        }catch(ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with patients data");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> findOneByEmail(@RequestParam String email){
        try{
            Patient patient = patientService.findOneByEmail(email);
            PatientDTO patientDTO = patientMapper.toResponse(patient);
            return ResponseEntity.ok(patientDTO);
        }catch(ClassCastException e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with patients data");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }




}
