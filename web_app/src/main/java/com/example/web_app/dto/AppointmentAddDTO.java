package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentAddDTO {
    private String doctorFullName;
    private String doctorPosition;
    private LocalDateTime dateTime;
    private String reason;
}
