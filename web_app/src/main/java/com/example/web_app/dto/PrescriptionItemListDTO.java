package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrescriptionItemListDTO {
    private String name;
    private String issuedAt;
    private String expiredAt;
}
