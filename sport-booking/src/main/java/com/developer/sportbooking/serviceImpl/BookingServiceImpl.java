package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.repository.BookingRepo;
import com.developer.sportbooking.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    BookingRepo bookingRepo;

    public BookingServiceImpl(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }
    @Override
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }
}
