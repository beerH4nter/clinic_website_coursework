package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TestAddDTO {
    private String name;
    private LocalDateTime dateTime;
}
