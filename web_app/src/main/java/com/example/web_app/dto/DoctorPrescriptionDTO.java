package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorPrescriptionDTO {
    private String name;
    private String patientFullName;
    private String issuedAt;
    private String expiredAt;

}
