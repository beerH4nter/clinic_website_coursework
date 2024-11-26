package com.example.web_app.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@ToString
public class PatientDTO {

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "surname can not be empty")
    private String surname;

    @NotEmpty(message = "patronymic can not be empty")
    private String patronymic;

    @NotEmpty(message = "date of birth cannot be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$", message = "Invalid date format (dd.MM.yyyy)")
    private String dateOfBirth;

    @NotEmpty(message = "email cannot be empty")
    private String email;

    @NotEmpty(message = "password can not be empty")
    private String password;

    private List<Integer> testsId;

}
