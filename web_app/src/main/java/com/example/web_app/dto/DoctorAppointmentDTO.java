package com.example.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointmentDTO {
    private String patientFullName;
    private LocalDateTime dateTime;
    private String reason;
    private String status;
    private String drugs;
    private String diagnose;
    private String doctorNotes;
}
