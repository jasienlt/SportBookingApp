package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.BookingStatus;
import com.developer.sportbooking.repository.BookingRepo;
import com.developer.sportbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    BookingRepo bookingRepo;
    private final DateService dateService;
    private final FieldTimeslotService fieldTimeslotService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final ReservedFieldTimeslotService reservedFieldTimeslotService;

    @Autowired
    public BookingServiceImpl(BookingRepo bookingRepo,
                              DateService dateService,
                              FieldTimeslotService fieldTimeslotService,
                              CustomerService customerService,
                              PaymentService paymentService,
                              ReservedFieldTimeslotService reservedFieldTimeslotService) {
        this.bookingRepo = bookingRepo;
        this.dateService = dateService;
        this.fieldTimeslotService = fieldTimeslotService;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.reservedFieldTimeslotService = reservedFieldTimeslotService;
    }

    @Override
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }

    @Override
    public void saveBookingSummary(Long selectedStartTimeslot,
                                   Long selectedEndTimeslot,
                                   List<Integer> dates,
                                   String selectedFieldsString,
                                   String totalFee,
                                   String bookingPeriodString,
                                   String sessionId) {

        List<Integer> selectedDates = new ArrayList<>();
        List<Long> selectedFields = new ArrayList<>();

        LocalDate bookingPeriod = dateService.convertStringToLocalDate(bookingPeriodString);

        if (dates.contains(8)) {
            for (int i = 1; i < 8; i++) {
                selectedDates.add(i);
            }
        } else {
            selectedDates = dates;
        }

        for (String s : selectedFieldsString.split(" ")) {
            selectedFields.add(Long.parseLong(s));
        }

        List<FieldTimeslot> fieldTimeslots = fieldTimeslotService.findFieldTimeslotByListId(selectedFields, selectedStartTimeslot, selectedEndTimeslot, selectedDates);

        Customer customer = customerService.getCustomerById(1L); // Ideally, get customer by session or ID
        Payment payment = paymentService.findPaymentById(2L); // Adjust accordingly

        Booking booking = new Booking((double) Float.parseFloat(totalFee), customer, payment, BookingStatus.PENDING, sessionId);

        this.saveBooking(booking);
        reservedFieldTimeslotService.saveReservedFieldTimeslots(booking, fieldTimeslots, bookingPeriod);

    }

    @Override
    public Booking getBookingBySessionId(String sessionId) {
        return bookingRepo.findBookingBySessionId(sessionId);
    }
}
