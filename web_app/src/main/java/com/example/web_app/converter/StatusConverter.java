package com.example.web_app.converter;

import com.example.web_app.enums.Status;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) // Если хотите, чтобы конвертер применялся ко всем enum этого типа
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return (status != null) ? status.getContext() : null;
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        return (dbData != null) ? Status.fromValue(dbData) : null;
    }
}
