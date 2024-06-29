package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.ReservedFieldTimeslot;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public interface ReservedFieldTimeslotService {
    public void saveReservedFieldTimeslots(Booking booking, List<FieldTimeslot> fieldTimeslots, LocalDate bookingPeriod);
    public List<ReservedFieldTimeslot> getAllReservedFieldBetweenTimePeriod(Date firstDate, Date lastDate);
}
