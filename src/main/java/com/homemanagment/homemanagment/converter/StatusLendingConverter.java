package com.homemanagment.homemanagment.converter;

import com.homemanagment.homemanagment.model.StatusLending;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class StatusLendingConverter implements AttributeConverter<StatusLending,String> {
    @Override
    public String convertToDatabaseColumn(StatusLending attribute) {
        if (attribute == null){
            return null;
        }
        return attribute.getDescription();
    }

    @Override
    public StatusLending convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        return Stream.of(StatusLending.values())
                .filter(d -> d.getDescription().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
