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
    private Integer id;
    private Date date;

    @Nullable
    private Integer customerId;

    @Nullable
    private Integer fieldId;

    @Nullable
    private Integer timeslotId;

    public BookingDto(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }
}
