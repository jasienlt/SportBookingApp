package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.*;
import com.developer.sportbooking.enumType.BookingStatus;
import com.developer.sportbooking.enumType.PaymentStatus;
import com.developer.sportbooking.repository.BookingRepo;
import com.developer.sportbooking.repository.FieldTimeslotRepo;
import com.developer.sportbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final FieldTimeslotRepo fieldTimeslotRepo;
    BookingRepo bookingRepo;
    private final DateService dateService;
    private final FieldTimeslotService fieldTimeslotService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final ReservedFieldTimeslotService reservedFieldTimeslotService;
    private final FieldService fieldService;

    @Autowired
    public BookingServiceImpl(BookingRepo bookingRepo,
                              DateService dateService,
                              FieldTimeslotService fieldTimeslotService,
                              CustomerService customerService,
                              PaymentService paymentService,
                              ReservedFieldTimeslotService reservedFieldTimeslotService,
                              FieldService fieldService, FieldTimeslotRepo fieldTimeslotRepo) {
        this.bookingRepo = bookingRepo;
        this.dateService = dateService;
        this.fieldTimeslotService = fieldTimeslotService;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.reservedFieldTimeslotService = reservedFieldTimeslotService;
        this.fieldService = fieldService;
        this.fieldTimeslotRepo = fieldTimeslotRepo;
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
                                   String sessionId,
                                   String method) {

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

        Booking booking = new Booking(Date.valueOf(LocalDate.now()),(double) Float.parseFloat(totalFee), customer, payment, BookingStatus.PENDING, sessionId);
        Payment paymentObj = new Payment(Date.valueOf(LocalDate.now()), method, sessionId, PaymentStatus.PENDING, booking.getId());

        this.saveBooking(booking);
        paymentService.savePayment(paymentObj);
        reservedFieldTimeslotService.saveReservedFieldTimeslots(booking, fieldTimeslots, bookingPeriod);

    }

    @Override
    public Booking getBookingBySessionId(String sessionId) {
        return bookingRepo.findBookingBySessionId(sessionId);
    }

}
