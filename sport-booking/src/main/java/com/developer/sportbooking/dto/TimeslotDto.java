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
    private Integer id;
    private Time startTime;
    private Time endTime;

    @Nullable
    private Integer fieldId;

    public TimeslotDto(Integer id, Time startTime, Time endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
