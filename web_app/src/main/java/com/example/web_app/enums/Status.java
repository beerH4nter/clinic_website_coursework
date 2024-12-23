package com.example.web_app.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    SCHEDULED("scheduled"),
    COMPLETED("completed"),
    CANCELED("canceled"),
    IN_PROGRESS("in progress");

    private final String context;


    @JsonCreator
    public static Status fromValue(String value) {
        for (Status color : values()) {
            if (color.getContext().equalsIgnoreCase(value)) {
                return color;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

}
