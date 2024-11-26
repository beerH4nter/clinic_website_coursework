package com.example.web_app.converter;

import com.example.web_app.enums.Reason;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) // Если хотите, чтобы конвертер применялся ко всем enum этого типа
public class ReasonConverter implements AttributeConverter<Reason, String> {

    @Override
    public String convertToDatabaseColumn(Reason reason) {
        return (reason != null) ? reason.getContext() : null;
    }

    @Override
    public Reason convertToEntityAttribute(String dbData) {
        return (dbData != null) ? Reason.fromValue(dbData) : null;
    }
}
