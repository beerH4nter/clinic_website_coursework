package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DoctorAppointmentDTO {
    private String patientFullName;
    private LocalDateTime dateTime;
    private String reason;
    private String status;
    private String drugs;
    private String diagnose;
    private String doctorNotes;
}
