package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {
    private Long id;
    private String name;

    @Nullable
    private Long courtId;

    public FieldDto(String name, Long courtId) {
        this.name = name;
        this.courtId = courtId;
    }
}
