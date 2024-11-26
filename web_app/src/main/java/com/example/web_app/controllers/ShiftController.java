package com.example.web_app.controllers;

import com.example.web_app.dto.ShiftDTO;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Shift;
import com.example.web_app.mapper.ShiftMapper;
import com.example.web_app.repositories.DoctorsRepository;
import com.example.web_app.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shift")
public class ShiftController {
    private final ShiftService shiftService;
    private final ShiftMapper shiftMapper;

    @Autowired
    public ShiftController(ShiftService shiftService, DoctorsRepository doctorsRepository, ShiftMapper shiftMapper) {
        this.shiftService = shiftService;
        this.shiftMapper = shiftMapper;
    }

    @PostMapping("/save")
    public Shift saveShift(@RequestBody @Valid ShiftDTO shiftDTO){

        Shift shift = shiftMapper.toRequest(shiftDTO);

        shiftService.save(shift);
        return shift;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllShifts() {
        try{
            List<Shift> shifts = shiftService.findAll();
            List<ShiftDTO> shiftDTOS = shifts.stream()
                    .map(shiftMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(shiftDTOS);

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
            List<ShiftDTO> shiftDTOS = shifts.stream()
                    .map(shiftMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(shiftDTOS);

        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with shifts data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }


}
