package com.example.web_app.controllers;


import com.example.web_app.dto.DoctorDTO;
import com.example.web_app.dto.ShiftDTO;
import com.example.web_app.entity.Doctor;
import com.example.web_app.mapper.DoctorMapper;
import com.example.web_app.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @PostMapping("/save")
    public Doctor saveDoctor(@RequestBody @Valid DoctorDTO doctorDTO){

        Doctor doctor = doctorMapper.toRequest(doctorDTO);
        doctorService.save(doctor);
        return doctor;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDoctors(){
        try{
            List<Doctor> doctors = doctorService.findAll();
            List<DoctorDTO> doctorDTOS = doctors.stream()
                    .map(doctorMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());

            return ResponseEntity.ok(doctorDTOS);

        }catch (ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with doctors data");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findOneById(@RequestParam int id){
        try{
            Doctor doctor = doctorService.findOneById(id);
            DoctorDTO doctorDTO = doctorMapper.toResponse(doctor);
            return ResponseEntity.ok(doctorDTO);
        }catch (ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with doctors data");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }

    }


}
