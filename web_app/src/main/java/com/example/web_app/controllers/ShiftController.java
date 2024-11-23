package com.example.web_app.controllers;

import com.example.web_app.dto.ShiftRequest;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Shift;
import com.example.web_app.repositories.DoctorsRepository;
import com.example.web_app.service.DoctorService;
import com.example.web_app.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shift")
public class ShiftController {
    private final ShiftService shiftService;
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public ShiftController(ShiftService shiftService, DoctorsRepository doctorsRepository) {
        this.shiftService = shiftService;
        this.doctorsRepository = doctorsRepository;
    }

    @PostMapping("/save")
    public Shift saveShift(@RequestBody @Valid ShiftRequest shiftRequest){

        Doctor doctor = doctorsRepository.findById(shiftRequest.getDoctorId()).orElseThrow(() ->
                new RuntimeException("Doctor with ID " + shiftRequest.getDoctorId() + " not found"));

        Shift shift = new Shift();
        shift.setDate(shiftRequest.getDate());
        shift.setTimeStart(shiftRequest.getTimeStart());
        shift.setTimeEnd(shiftRequest.getTimeEnd());
        shift.setLunchTime(shiftRequest.getLunchTime());
        shift.setDoctor(doctor);

        System.out.println(shift);
        System.out.println(shiftRequest.toString());
        shiftService.save(shift);
        return shift;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllShifts() {
        try{
            List<Shift> shifts = shiftService.findAll();
            return ResponseEntity.ok(shifts);
        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with shifts data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("/findAllByDoctor")
    public ResponseEntity<?> findAllShiftsByDoctorId(@RequestParam int id){
        try {
            List<Shift> shifts = shiftService.findAllShiftsByDoctorId(id);
            return ResponseEntity.ok(shifts);

        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with shifts data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }


}
