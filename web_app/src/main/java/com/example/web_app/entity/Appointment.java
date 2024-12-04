package com.example.web_app.entity;


import com.example.web_app.converter.ReasonConverter;
import com.example.web_app.converter.StatusConverter;
import com.example.web_app.enums.Reason;
import com.example.web_app.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    private Status status;

    @Column(name = "reason")
    @Convert(converter = ReasonConverter.class)
    private Reason reason;

    @Column(name = "diagnose")
    @NotEmpty(message = "diagnose can not be empty")
    private String diagnose;

    @Column(name = "drugs")
    @NotEmpty(message = "drugs can not be empty")
    private String drugs;

    @Column(name = "doctor_notes")
    private String doctorNotes;


    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "disease_id", nullable = true)
    private Disease disease;
}
