package com.example.web_app.entity;

import com.example.web_app.converter.ReasonConverter;
import com.example.web_app.converter.StatusConverter;
import com.example.web_app.enums.Reason;
import com.example.web_app.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "date or time can not be empty")
    private LocalDateTime dateTime;

    @Convert(converter = ReasonConverter.class)
    @NotEmpty(message = "reason can not be empty")
    private Reason reason;

    @Convert(converter = StatusConverter.class)
    @NotEmpty(message = "status can not be empty")
    private Status status;

    @NotEmpty(message = "diagnose can noe be empty")
    @Column(length = 512)
    private String diagnose;

    private String doctorNotes;

    @Column(length = 512)
    private String drugs;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;


}
