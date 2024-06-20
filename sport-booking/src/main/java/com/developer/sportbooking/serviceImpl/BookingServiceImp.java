package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.repository.BookingRepo;
import com.developer.sportbooking.service.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImp implements BookingService {
    BookingRepo bookingRepo;

    public BookingServiceImp(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }
    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepo.save(booking);
    }
}
