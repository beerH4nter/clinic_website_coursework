package com.example.web_app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ShiftDTO {
    @NotEmpty(message = "Date cannot be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$", message = "Invalid date format (dd.MM.yyyy)")
    private String date;

    @NotEmpty(message = "Time start cannot be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Invalid time format (HH:mm)")
    private String timeStart;

    @NotEmpty(message = "Time end cannot be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Invalid time format (HH:mm)")
    private String timeEnd;

    @NotEmpty(message = "Lunch time cannot be empty")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Invalid time format (HH:mm)")
    private String lunchTime;

    private int doctorId; // ID доктора, связанного с этим сменой

    private String doctorSurname;

}
