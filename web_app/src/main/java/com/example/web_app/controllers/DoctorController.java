package com.example.web_app.controllers;

import com.example.web_app.dto.DoctorAppointmentDTO;
import com.example.web_app.dto.DoctorPrescriptionAddDTO;
import com.example.web_app.dto.DoctorPrescriptionDTO;
import com.example.web_app.dto.UpdateAppointmentDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.entity.Prescription;
import com.example.web_app.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService service;


    @GetMapping("/appointmentsToday/{doctorId}")
    public ResponseEntity<List<DoctorAppointmentDTO>> getAppointmentsToday(@PathVariable Long doctorId){
        return ResponseEntity.ok(service.getDoctorAppointmentsToday(doctorId));
    }

    @GetMapping("/doctorAppointments/{doctorId}")
    public ResponseEntity<List<DoctorAppointmentDTO>> getAppointments(@PathVariable Long doctorId){
        return ResponseEntity.ok(service.getDoctorAppointments(doctorId));
    }
    @GetMapping("/doctorPrescriptions/{doctorId}")
    public ResponseEntity<List<DoctorPrescriptionDTO>> getPrescriptions(@PathVariable Long doctorId){
        return ResponseEntity.ok(service.getDoctorPrescriptions(doctorId));
    }

    @PostMapping("/addPrescription")
    public void addPrescription(@RequestBody @Valid DoctorPrescriptionAddDTO doctorPrescriptionAddDTO){
        service.addPrescription(doctorPrescriptionAddDTO);
    }

    public ResponseEntity<Appointment> updateTodayAppointment(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String patronymic,
            @RequestParam Long doctorId,
            @RequestBody UpdateAppointmentDTO dto
    ) {
        Appointment updatedAppointment = service.updateTodayAppointment(name, surname, patronymic, doctorId, dto);
        return ResponseEntity.ok(updatedAppointment);
    }

}
