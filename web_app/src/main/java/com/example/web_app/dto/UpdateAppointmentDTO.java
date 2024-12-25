package com.example.web_app.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAppointmentDTO {
    private String diagnose;
    private String doctorNotes;
    private String drugs;
    private String status; // Можно передавать статус как строку, если это enum
}

