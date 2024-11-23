package com.example.web_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Reason {
    CONSULTATION("consultation"),
    REPEATED("repeated ");

    private final String context;
}
