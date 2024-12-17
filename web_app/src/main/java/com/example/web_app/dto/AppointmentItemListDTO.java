package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AppointmentItemListDTO {
    private Long id;
    private LocalDateTime dateTime;
    private String reason;
    private String status;

}
