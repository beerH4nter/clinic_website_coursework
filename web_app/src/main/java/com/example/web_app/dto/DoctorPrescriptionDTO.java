package com.example.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPrescriptionDTO {
    private String name;
    private String patientFullName;
    private String issuedAt;
    private String expiredAt;

}
