package com.example.web_app.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentDTO {
    private String diagnose;
    private String doctorNotes;
    private String drugs;
    private String status; // Можно передавать статус как строку, если это enum
}

