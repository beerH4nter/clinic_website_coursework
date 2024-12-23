package com.example.web_app.entity;

import com.example.web_app.converter.StatusConverter;
import com.example.web_app.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "tests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "date and time can not be empty")
    private LocalDateTime dateTime;

    @NotEmpty(message = "status can not be empty")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @Column(length = 4096)
    private String result;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
}
