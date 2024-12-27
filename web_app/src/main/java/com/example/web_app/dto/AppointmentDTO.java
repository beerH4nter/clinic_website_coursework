package com.example.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
public class AppointmentDTO {
    private Long id;
    private LocalDateTime dateTime;
    private String reason;
    private String status;
    private String doctorFullName;
    private String doctorPosition;
    private String drugs;
    private String diagnose;
    private String doctorNotes;
}
