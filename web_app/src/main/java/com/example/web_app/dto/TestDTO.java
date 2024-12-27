package com.example.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Statement;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    private String name;
    private LocalDateTime dateTime;
    private String status;
    private String result;
}
