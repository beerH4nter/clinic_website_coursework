package com.example.web_app.entity;

import lombok.*;

import javax.persistence.*;
import javax.print.Doc;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "shifts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shift {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    @NotEmpty(message = "Can not be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String date;

    @Column(name = "time_start")
    @NotEmpty(message = "Can not be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    private String timeStart;

    @Column(name = "time_end")
    @NotEmpty(message = "Can not be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    private String timeEnd;

    @Column(name = "lunch_time")
    @NotEmpty(message = "Can not be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    private String lunchTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;


}
