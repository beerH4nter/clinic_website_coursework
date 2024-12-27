package com.example.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentAddDTO {
    private Long patientId;
    private String doctorFullName;
    private String doctorPosition;
    private LocalDateTime dateTime;
    private String reason;
}
