package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.service.DateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;

@Service
public class DateServiceImpl implements DateService {
    @Override
    public LocalDate convertStringToLocalDate(String s) {
        LocalDate bookingPeriod = LocalDate.now();
        int bookingPeriodMonth = Integer.parseInt(s.substring(s.length() - 2));
        int bookingPeriodYear = Integer.parseInt(s.substring(0, 4));
        if(bookingPeriodMonth > LocalDate.now().getMonthValue()) {
            bookingPeriod = Year.of(bookingPeriodYear).atMonth(bookingPeriodMonth).atDay(1);
        }

        return bookingPeriod;
    }
}
