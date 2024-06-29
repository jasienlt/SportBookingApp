package com.developer.sportbooking.enumConverter;

import com.developer.sportbooking.enumType.DayOfWeek;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DayOfWeekConverter implements AttributeConverter<DayOfWeek, String> {
    @Override
    public String convertToDatabaseColumn(DayOfWeek dayOfWeek) {
        if (dayOfWeek == null) {
            return null;
        }
        return dayOfWeek.name();
    }

    @Override
    public DayOfWeek convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return DayOfWeek.valueOf(dbData);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid value for DayOfWeek: " + dbData, ex);
        }
    }
}
