package com.example.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String name;
    private String surname;
    private String patronymic;
    private String dateOfBirth;
    private String email;
    private String password;
}
