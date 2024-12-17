package com.example.web_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
    private Integer code;
    private String errorMessage;
    private String serverError;
}
