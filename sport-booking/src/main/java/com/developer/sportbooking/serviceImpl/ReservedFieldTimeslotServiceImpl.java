package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.ReservedFieldTimeslot;
import com.developer.sportbooking.enumType.BookingStatus;
import com.developer.sportbooking.repository.FieldTimeslotRepo;
import com.developer.sportbooking.repository.ReservedFieldTimeslotRepo;
import com.developer.sportbooking.service.ReservedFieldTimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservedFieldTimeslotServiceImpl implements ReservedFieldTimeslotService {
    ReservedFieldTimeslotRepo reservedFieldTimeslotRepo;

    @Autowired
    public ReservedFieldTimeslotServiceImpl(ReservedFieldTimeslotRepo reservedFieldTimeslotRepo) {
        this.reservedFieldTimeslotRepo = reservedFieldTimeslotRepo;
    }

    @Override
    public void saveReservedFieldTimeslots(Booking booking, List<FieldTimeslot> fieldTimeslots, LocalDate bookingPeriod) {
        List<ReservedFieldTimeslot> reservedFieldTimeslots = new ArrayList<>();

        LocalDate today = LocalDate.now();

        for (FieldTimeslot fieldTimeslot : fieldTimeslots) {
            int day = fieldTimeslot.getDay().getValue();
            DayOfWeek dow = DayOfWeek.of(day);
            LocalDate date = Year.of(bookingPeriod.getYear()).atMonth(bookingPeriod.getMonth()).atDay(1).with(TemporalAdjusters.firstInMonth(dow));
            Month mi = bookingPeriod.getMonth();
            while (mi == bookingPeriod.getMonth()) {
                if(date.isAfter(today) || date.isEqual(today)) {
                    ReservedFieldTimeslot reservedFieldTimeslot = new ReservedFieldTimeslot(booking, fieldTimeslot, Date.valueOf(date));
                    reservedFieldTimeslots.add(reservedFieldTimeslot);
                    fieldTimeslot.getReservedFieldTimeslots().add(reservedFieldTimeslot);
                    booking.getReservedFieldTimeslots().add(reservedFieldTimeslot);
                }
                date = date.with(TemporalAdjusters.next(dow));
                mi = date.getMonth();
            }
        }

        reservedFieldTimeslotRepo.saveAll(reservedFieldTimeslots);
    }

    @Override
    public HashMap<BookingStatus, List<ReservedFieldTimeslot>> getAllReservedFieldBetweenTimePeriod(Date firstDate, Date lastDate) {
        HashMap<BookingStatus, List<ReservedFieldTimeslot>> bookingStatusListMap = new HashMap<>();

        bookingStatusListMap.put(BookingStatus.PENDING, new ArrayList<>());
        bookingStatusListMap.put(BookingStatus.COMPLETED, new ArrayList<>());

        List<ReservedFieldTimeslot> reservedFieldTimeslots = reservedFieldTimeslotRepo.findReservedFieldTimeslotByBookingDateBetween(firstDate, lastDate);

        for (ReservedFieldTimeslot reservedFieldTimeslot : reservedFieldTimeslots) {
            BookingStatus bookingStatus = reservedFieldTimeslot.getBooking().getStatus();
            bookingStatusListMap.get(bookingStatus).add(reservedFieldTimeslot);
        }

        return bookingStatusListMap;
    }

    @Override
    public void removeReservedFieldByBooking(Booking booking) {
        List<ReservedFieldTimeslot> reservedFieldTimeslots = reservedFieldTimeslotRepo.findReservedFieldTimeslotByBooking(booking);

        for (ReservedFieldTimeslot reservedFieldTimeslot : reservedFieldTimeslots) {
            booking.removeReservedFieldTimeslot(reservedFieldTimeslot);
            reservedFieldTimeslot.getFieldTimeslot().removeReservedFieldTimeslot(reservedFieldTimeslot);

            reservedFieldTimeslotRepo.delete(reservedFieldTimeslot);
        }
    }
}
