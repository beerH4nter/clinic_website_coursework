package com.example.web_app.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "patients")
@Getter
@Setter
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
    @NotEmpty(message = "Can not be empty")
    private String patronymic;

    @Column(name = "date_of_birth")
    @NotEmpty(message = "Can not be empty")
    private String dateOfBirth;

    @Email(message = "not format email")
    @Column(name = "email", unique = true)
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String email;

    @Column(name = "password")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String password;

}
