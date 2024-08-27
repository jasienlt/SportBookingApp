package com.developer.sportbooking.chartDto;

import com.developer.sportbooking.enumType.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class BookingCount {
    private Date bookingDate;
    private BookingStatus bookingStatus;
    private Long total;


}
