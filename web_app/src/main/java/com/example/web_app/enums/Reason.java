package com.example.web_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Reason {
    CONSULTATION("consultation"),
    REPEATED("repeated ");

    private final String context;

    public static Reason fromValue(String value) {
        for (Reason reason : values()) {
            if (reason.getContext().equalsIgnoreCase(value)) {
                return reason;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
