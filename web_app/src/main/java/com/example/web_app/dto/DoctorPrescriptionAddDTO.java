package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorPrescriptionAddDTO {
    private String name;
    private String patientFullName;
    private String doctorFullName;
    private String issuedAt;
    private String expiredAt;

}
