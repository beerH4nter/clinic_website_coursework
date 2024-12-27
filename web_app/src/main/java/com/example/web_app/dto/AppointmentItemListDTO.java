package com.example.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppointmentItemListDTO {
    private Long id;
    private LocalDateTime dateTime;
    private String reason;
    private String status;

}
