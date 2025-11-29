package com.javarush.entity.converter;

import com.javarush.entity.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) return null;
        return rating.getLabel();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        if (s == null) return null;
        return Rating.fromDb(s);
    }
}
