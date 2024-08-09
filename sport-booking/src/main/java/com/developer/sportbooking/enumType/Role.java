package com.developer.sportbooking.enumType;

import lombok.Getter;

@Getter
public enum Role {
    OWNER(1),
    ADMIN(2),
    CUSTOMER(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role of(int value) {
        for (Role role : Role.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
