package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesItemListDTO {
    private Long offerId;
    private String name;
}
