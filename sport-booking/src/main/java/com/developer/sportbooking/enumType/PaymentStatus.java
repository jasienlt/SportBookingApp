package com.developer.sportbooking.enumType;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    SUCCESSFUL(1),
    PENDING(2),
    CANCELED(3);

    private final int value;

    PaymentStatus(int value) {
        this.value = value;
    }

    public static PaymentStatus of(int value) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.getValue() == value) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
