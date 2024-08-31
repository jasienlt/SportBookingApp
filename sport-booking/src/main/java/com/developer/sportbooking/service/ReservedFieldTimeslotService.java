package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.ReservedFieldTimeslot;
import com.developer.sportbooking.enumType.BookingStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


@Service
public interface ReservedFieldTimeslotService {
    void saveReservedFieldTimeslots(Booking booking, List<FieldTimeslot> fieldTimeslots, LocalDate bookingPeriod);
    HashMap<BookingStatus, List<ReservedFieldTimeslot>> getAllReservedFieldBetweenTimePeriod(Date firstDate, Date lastDate);
    void removeReservedFieldByBooking(Booking booking);
    List<ReservedFieldTimeslot> getReservedFieldTimeslotByBooking(Booking booking);
}
