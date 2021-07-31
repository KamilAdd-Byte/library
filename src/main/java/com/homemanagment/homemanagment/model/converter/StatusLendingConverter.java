package com.homemanagment.homemanagment.model.converter;

import com.homemanagment.homemanagment.model.type.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class StatusLendingConverter implements AttributeConverter<BookStatus,String> {
    @Override
    public String convertToDatabaseColumn(BookStatus attribute) {
        if (attribute == null){
            return null;
        }
        return attribute.getDescription();
    }

    @Override
    public BookStatus convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        return Stream.of(BookStatus.values())
                .filter(d -> d.getDescription().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
