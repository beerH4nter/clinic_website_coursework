package com.example.web_app.controllers;

import com.example.web_app.dto.AppointmentDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.mapper.AppointmentMapper;
import com.example.web_app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentMapper appointmentMapper, AppointmentService appointmentService) {
        this.appointmentMapper = appointmentMapper;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/save")
    public Appointment save(@RequestBody @Valid AppointmentDTO appointmentDTO){

        Appointment appointment = appointmentMapper.toRequest(appointmentDTO);
        appointmentService.save(appointment);

        return appointment;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAppointments(){
        try{
            List<Appointment> appointments = appointmentService.findAll();
            List<AppointmentDTO> appointmentDTOS = appointments.stream()
                    .map(appointmentMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(appointmentDTOS);

        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with appointments data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("/findByPatient")
    public ResponseEntity<?> findAllAppointmentsByPatientId(@RequestParam int id){
        try {
            List<Appointment> appointments = appointmentService.findAllByPatientId(id);
            List<AppointmentDTO> appointmentDTOS = appointments.stream()
                    .map(appointmentMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(appointmentDTOS);

        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with appointments data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("/findByDoctor")
    public ResponseEntity<?> findAllAppointmentsByDoctorId(@RequestParam int id){
        try {
            List<Appointment> appointments = appointmentService.findAllByDoctorId(id);
            List<AppointmentDTO> appointmentDTOS = appointments.stream()
                    .map(appointmentMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(appointmentDTOS);

        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with appointments data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

    @GetMapping("/findByDisease")
    public ResponseEntity<?> findAllAppointmentsByDiseaseId(@RequestParam int id){
        try {
            List<Appointment> appointments = appointmentService.findAllByDiseaseId(id);
            List<AppointmentDTO> appointmentDTOS = appointments.stream()
                    .map(appointmentMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());
            return ResponseEntity.ok(appointmentDTOS);

        }catch (ClassCastException e){
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with appointments data"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error"));
        }
    }

}
