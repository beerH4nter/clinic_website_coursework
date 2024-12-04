package com.example.web_app.dto;

import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Patient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PrescriptionDTO {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String issuedAt;

    @NotEmpty
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String expiredAt;

    private int patientId;

    private int doctorId;

    private String doctorSurname;

    private String patientSurname;

}
