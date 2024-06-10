package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeslotDto {
    private Long id;
    private Time startTime;
    private Time endTime;

    @Nullable
    private Long fieldId;

    public TimeslotDto(Time startTime, Time endTime, @Nullable Long fieldId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fieldId = fieldId;
    }

}
