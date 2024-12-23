package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDTO {
    private LocalDateTime dateTime;
    private String reason;
    private String status;
    private String doctorFullName;
    private String doctorPosition;
    private String drugs;
    private String diagnose;
    private String doctorNotes;
}
