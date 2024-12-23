package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TestsItemListDTO {
    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private String status;
}
