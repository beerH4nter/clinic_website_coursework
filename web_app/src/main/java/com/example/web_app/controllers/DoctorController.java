package com.example.web_app.controllers;


import com.example.web_app.entity.Doctor;
import com.example.web_app.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/save")
    public Doctor saveDoctor(@RequestBody @Valid Doctor doctor){
        doctorService.save(doctor);
        return doctor;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDoctors(){
        try{
            List<Doctor> doctors = doctorService.findAll();
            return ResponseEntity.ok(doctors);
        }catch (ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with doctors data");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }

    @GetMapping("/findOne")
    public ResponseEntity<?> findOneByName(@RequestParam String name){
        try{
            Doctor doctor = doctorService.findOneByName(name);
            return ResponseEntity.ok(doctor);
        }catch (ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with doctors data");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }

    }


}
