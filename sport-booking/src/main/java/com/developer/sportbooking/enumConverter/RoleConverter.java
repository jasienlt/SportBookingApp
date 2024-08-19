package com.developer.sportbooking.enumConverter;

import com.developer.sportbooking.enumType.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class  RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.name();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return Role.valueOf(dbData);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid value for Role: " + dbData, ex);
        }
    }
}
