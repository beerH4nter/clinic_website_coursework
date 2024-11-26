package com.example.web_app.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    SHEDULED("scheduled"),
    COMPLETED("completed"),
    CANCELED("canceled"),
    IN_PROGRESS("in progress");

    private final String context;

    public static Status fromValue(String value) {
        for (Status color : values()) {
            if (color.getContext().equalsIgnoreCase(value)) {
                return color;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

}
