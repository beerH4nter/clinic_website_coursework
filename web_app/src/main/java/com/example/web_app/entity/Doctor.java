package com.example.web_app.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {


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

    @Column(name = "position")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String position;

    @Column(name = "date_of_birth")
    @NotEmpty(message = "Can not be empty")
    private String dateOfBirth;

    @Email
    @Column(name = "email")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String email;

    @Column(name = "password")
    @Size(min = 3, max = 50, message = "Wrong size of column")
    @NotEmpty(message = "Can not be empty")
    private String password;



}
