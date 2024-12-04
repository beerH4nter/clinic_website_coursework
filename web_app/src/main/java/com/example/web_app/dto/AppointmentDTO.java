package com.example.web_app.dto;

import com.example.web_app.converter.ReasonConverter;
import com.example.web_app.converter.StatusConverter;
import com.example.web_app.enums.Reason;
import com.example.web_app.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class AppointmentDTO {

    @NotEmpty(message = "date can not be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String date;

    @NotEmpty(message = "time can not be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$")
    private String time;

    @Convert(converter = StatusConverter.class)
    private Status status;

    @Convert(converter = ReasonConverter.class)
    private Reason reason;

    @NotEmpty(message = "diagnose can not be empty")
    private String diagnose;

    @NotEmpty(message = "drugs can not be empty")
    private String drugs;


    private String doctorNotes;

    private int patientId;

    private int doctorId;

    private int diseaseId;

    private String patientSurname;

    private String doctorSurname;

    private String diseaseName;
}
