package com.example.web_app.entity;

import com.example.web_app.converter.ReasonConverter;
import com.example.web_app.converter.StatusConverter;
import com.example.web_app.enums.Reason;
import com.example.web_app.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

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

    private LocalDateTime dateTime;

    @Convert(converter = ReasonConverter.class)
    private Reason reason;

    @Convert(converter = StatusConverter.class)
    private Status status;

    @NotEmpty(message = "diagnose can noe be empty")
    @Column(length = 1024)
    private String diagnose;

    @Column(length = 4096)
    private String doctorNotes;

    @Column(length = 512)
    private String drugs;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;


}
