package com.example.web_app.controllers;

import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Patient;
import com.example.web_app.service.DoctorService;
import com.example.web_app.service.PatientService;
import javax.validation.Valid;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/save")
    public Patient savePatient(@RequestBody @Valid Patient patient){

        patientService.save(patient);

        return patient;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPatients(){
        try{
            List<Patient> patients = patientService.findAll();
            return ResponseEntity.ok(patients);
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with patients data");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }

    }

    @GetMapping("/findOne")
    public ResponseEntity<?> findOneByName(@RequestParam String name){
        try{
            Patient patient = patientService.findOneByName(name);
            return ResponseEntity.ok(patient.toString());
        }catch(ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with doctors data");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }



}
