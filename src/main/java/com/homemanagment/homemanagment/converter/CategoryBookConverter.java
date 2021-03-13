package com.homemanagment.homemanagment.converter;

import com.homemanagment.homemanagment.model.CategoryBook;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = false)
public class CategoryBookConverter implements AttributeConverter<CategoryBook,String> {
    @Override
    public String convertToDatabaseColumn(CategoryBook attribute) {
        switch (attribute) {
            case KIDS:
                return "KIDS";
            case PSYCHOLOGIST:
                return "PSYCH";
            case ROMANTIC:
                return "R";
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public CategoryBook convertToEntityAttribute(String description) {
        if (description == null){
            return null;
        }
        return Stream.of(CategoryBook.values())
                .filter(d -> d.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
