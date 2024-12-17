package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfferItemListDTO {
    private Long offerId;
    private String name;
}
