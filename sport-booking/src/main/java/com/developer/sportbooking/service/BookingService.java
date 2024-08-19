package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Court;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    void saveBooking(Booking booking);

    void saveBookingSummary(Long selectedStartTimeslot,
                               Long selectedEndTimeslot,
                               List<Integer> dates,
                               String selectedFieldsString,
                               String totalFee,
                               String bookingPeriodString,
                               String sessionId,
                               String method);

    Booking getBookingBySessionId(String sessionId);

//    List<Booking> findAllBooking();
//
//    Booking findBookingById(Integer id);
//
//    Booking findBookingByPaymentId(Integer id);
//
//    Booking updateBookingById(Booking booking, Integer id);
//
//    void deleteBookingById(Integer id);
}
