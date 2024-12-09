package com.example.web_app.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String name;

    @Column(name = "surname")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String surname;

    @Column(name = "patronymic")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    private String patronymic;

    @Column(name = "date_of_birth")
    @NotEmpty(message = "Can not be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String dateOfBirth;

    @UniqueElements
    @Email(message = "not format email")
    @Column(name = "email", unique = true)
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String email;

    @Column(name = "password")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String password;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> tests;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescriptions;
}
