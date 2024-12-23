package com.example.web_app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prescriptions")
@ToString
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "issue date can not be empty")
    private String name;

    @NotEmpty(message = "issue date can not be empty")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(19[0-9]{2}|20[0-2][0-9])$",
            message = "Date must be in dd.mm.yyyy format and in 1900-2029 diapason"
    )
    private String issuedAt;

    @NotEmpty(message = "expire date can not be empty")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(19[0-9]{2}|20[0-2][0-9])$",
            message = "Date must be in dd.mm.yyyy format and in 1900-2029 diapason"
    )
    private String expiredAt;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;


}
