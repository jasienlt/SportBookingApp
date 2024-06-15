package com.developer.sportbooking.dto;

import com.developer.sportbooking.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long id;
    private Date date;

    @Nullable
    private Long customerId;

    @Nullable
    private Long fieldId;

    @Nullable
    private Long timeslotId;

    public BookingDto(Date date, @Nullable Long customerId, @Nullable Long fieldId, @Nullable Long timeslotId) {
        this.date = date;
        this.customerId = customerId;
        this.fieldId = fieldId;
        this.timeslotId = timeslotId;
    }
}
