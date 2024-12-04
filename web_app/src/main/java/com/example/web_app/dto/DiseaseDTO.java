package com.example.web_app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class DiseaseDTO {

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "date_start can not be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String dateStart;

    @NotEmpty(message = "date_end can not be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.((19|20)\\d{2})$")
    private String dateEnd;

    @Column(name = "treatment")
    @NotEmpty(message = "treatment can not be empty")
    private String treatment;

    private List<Integer> appointmentsId;

}
