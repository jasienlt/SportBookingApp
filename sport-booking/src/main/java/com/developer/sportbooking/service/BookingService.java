package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);

    List<Booking> findAllBooking();

    Booking findBookingById(Integer id);

    Booking findBookingByPaymentId(Integer id);

    Booking updateBookingById(Booking booking, Integer id);

    void deleteBookingById(Integer id);
}
