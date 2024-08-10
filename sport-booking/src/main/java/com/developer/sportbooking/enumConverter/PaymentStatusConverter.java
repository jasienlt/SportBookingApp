package com.developer.sportbooking.enumConverter;

import com.developer.sportbooking.enumType.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
    @Override
    public String convertToDatabaseColumn(PaymentStatus bookingStatus) {
        if (bookingStatus == null) {
            return null;
        }
        return bookingStatus.name();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return PaymentStatus.valueOf(dbData);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid value for Payment: " + dbData, ex);
        }
    }
}