package com.javarush.entity.converter;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecialFeaturesConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> strings) {
        if (strings == null || strings.isEmpty()) return null;
        return String.join(",", strings);
    }

    @Override
    public Set<String> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) return null;
        return Arrays.stream(s.split(",")).collect(Collectors.toSet());
    }
}
