package com.example.web_app.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can not be empty")
    @Size(min = 3, max = 50)
    private String name;

    @NotEmpty(message = "surname can not be empty")
    @Size(min = 3, max = 50)
    private String surname;

    @NotEmpty(message = "patronymic can not be empty")
    @Size(min = 3, max = 50)
    private String patronymic;

    @NotEmpty(message = "position can not be empty")
    private String position;

    @NotEmpty(message = "date of birth can not be empty")
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(19[0-9]{2}|20[0-2][0-9])$",
            message = "Date must be in dd.mm.yyyy format and in 1900-2029 diapason"
    )
    private String dateOfBirth;

    @NotEmpty(message = "email can not be empty")
    @UniqueElements
    private String email;

    @NotEmpty(message = "password can not be empty")
    private String password;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;
}
