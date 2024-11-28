package com.example.web_app.controllers;

import com.example.web_app.dto.PrescriptionDTO;
import com.example.web_app.entity.Prescription;
import com.example.web_app.mapper.PrescriptionMapper;
import com.example.web_app.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final PrescriptionMapper prescriptionMapper;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, PrescriptionMapper prescriptionMapper) {
        this.prescriptionService = prescriptionService;
        this.prescriptionMapper = prescriptionMapper;
    }

    @PostMapping("/save")
    public Prescription savePrescription(@RequestBody @Valid PrescriptionDTO prescriptionDTO){
        Prescription prescription = prescriptionMapper.toRequest(prescriptionDTO);
        prescriptionService.save(prescription);
        return prescription;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPrescriptions(){
        try {
            List<Prescription> prescriptions = prescriptionService.findAll();

            List<PrescriptionDTO> prescriptionDTOS = prescriptions.stream()
                    .map(prescriptionMapper::toResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(prescriptionDTOS);
        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with prescriptions data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("/findByPatient")
    public ResponseEntity<?> findAllPrescriptionsByPatientId(@RequestParam int id){
        try{
            List<Prescription> prescriptions = prescriptionService.findAllByPatientId(id);
            List<PrescriptionDTO> prescriptionDTOS = prescriptions.stream()
                    .map(prescriptionMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(prescriptionDTOS);
        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with prescriptions data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("/findByDoctor")
    public ResponseEntity<?> findAllPrescriptionsByDoctorId(@RequestParam int id){
        try{
            List<Prescription> prescriptions = prescriptionService.findAllByDoctorId(id);
            List<PrescriptionDTO> prescriptionDTOS = prescriptions.stream()
                    .map(prescriptionMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(prescriptionDTOS);
        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with prescriptions data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }


}
