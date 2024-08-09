package com.developer.sportbooking.enumType;

import lombok.Getter;

@Getter
public enum BookingStatus {
    COMPLETED(1),
    PENDING(2),
    CANCELED(3);

    private final int value;

    BookingStatus(int value) {
        this.value = value;
    }

    public static BookingStatus of(int value) {
        for (BookingStatus bookingStatus : BookingStatus.values()) {
            if (bookingStatus.getValue() == value) {
                return bookingStatus;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
