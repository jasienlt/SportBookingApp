package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    Booking saveBooking(Booking booking);

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
