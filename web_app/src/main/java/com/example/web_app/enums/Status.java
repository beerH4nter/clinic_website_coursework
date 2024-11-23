package com.example.web_app.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    SHEDULED("scheduled"),
    COMPLETED("completed"),
    CANCELED("canceled"),
    IN_PROGRESS("in progress");

    private final String context;

}
