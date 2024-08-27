package com.developer.sportbooking.service;

import com.developer.sportbooking.config.CustomCustomerDetails;
import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Customer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                            String method,
                            Customer customer);

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
