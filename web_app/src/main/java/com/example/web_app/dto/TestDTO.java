package com.example.web_app.dto;

import com.example.web_app.converter.StatusConverter;
import com.example.web_app.entity.Test;
import com.example.web_app.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.Convert;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class TestDTO {
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "date cannot be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$", message = "Invalid date format (dd.MM.yyyy)")
    private String date;

    @NotEmpty(message = "time cannot be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Invalid time format (HH:mm)")
    private String time;

    @Convert(converter = StatusConverter.class)
    private Status status;

    @NotEmpty(message = "result can not be empty")
    private String result;

    private int patientId;

    private String patientSurname;


}
