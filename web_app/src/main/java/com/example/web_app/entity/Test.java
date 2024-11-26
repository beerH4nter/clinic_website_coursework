package com.example.web_app.entity;

import com.example.web_app.converter.StatusConverter;
import com.example.web_app.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Test {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 3, max = 50, message = "Wrong zie of th column")
    @NotEmpty(message = "name can not be empty")
    private String name;

    @Column(name = "date")
    @NotEmpty(message = "date can not be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String date;

    @Column(name = "time")
    @NotEmpty(message = "time can not be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    private String time;

    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    @NotEmpty(message = "Status can not be empty")
    private Status status;

    @Column(name = "result")
    @NotEmpty(message = "result can not be empty")
    private String result;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;



}
