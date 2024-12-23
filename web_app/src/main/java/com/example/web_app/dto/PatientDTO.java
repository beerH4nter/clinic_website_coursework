package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientDTO {
    private String name;
    private String surname;
    private String patronymic;
    private String dateOfBirth;
    private String email;
}
