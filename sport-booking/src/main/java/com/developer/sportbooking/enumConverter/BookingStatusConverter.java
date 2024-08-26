package com.developer.sportbooking.enumConverter;

import com.developer.sportbooking.enumType.BookingStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BookingStatusConverter implements AttributeConverter<BookingStatus, String> {
    @Override
    public String convertToDatabaseColumn(BookingStatus bookingStatus) {
        if (bookingStatus == null) {
            return null;
        }
        return bookingStatus.name();
    }

    @Override
    public BookingStatus convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return BookingStatus.valueOf(dbData);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid value for BookingStatus: " + dbData, ex);
        }
    }
}
