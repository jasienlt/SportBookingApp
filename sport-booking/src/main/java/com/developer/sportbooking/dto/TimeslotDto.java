package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeslotDto {
    private Integer id;
    private Time startTime;
    private Time endTime;

}
