package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OfferDTO {
    private String name;
    private String description;
    private Double price;
    private Boolean isSale;
}
