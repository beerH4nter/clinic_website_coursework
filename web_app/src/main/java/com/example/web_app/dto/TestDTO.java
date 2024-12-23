package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Statement;
import java.time.LocalDateTime;

@Data
@Builder
public class TestDTO {
    private String name;
    private LocalDateTime dateTime;
    private String status;
    private String result;
}
