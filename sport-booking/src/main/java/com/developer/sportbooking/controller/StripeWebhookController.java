package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.service.*;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StripeWebhookController {

    @Value("${END_POINT_SECRET}")
    private String endpointSecret;  // Replace with your webhook secret

    private final BookingService bookingService;
    private final DateService dateService;
    private final FieldTimeslotService fieldTimeslotService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final ReservedFieldTimeslotService reservedFieldTimeslotService;

    @Autowired
    public StripeWebhookController(BookingService bookingService, DateService dateService,
                                   FieldTimeslotService fieldTimeslotService, CustomerService customerService,
                                   PaymentService paymentService, ReservedFieldTimeslotService reservedFieldTimeslotService) {
        this.bookingService = bookingService;
        this.dateService = dateService;
        this.fieldTimeslotService = fieldTimeslotService;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.reservedFieldTimeslotService = reservedFieldTimeslotService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeEvent(@RequestBody String payload,
                                                    @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            // Invalid signature
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        }

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
            if (session != null) {
                // Process the session object
                String customerEmail = session.getCustomerEmail();
                System.out.println(customerEmail);
                Map<String, String> metadata = session.getMetadata();

                Long selectedStartTimeslot = Long.parseLong(metadata.get("selectedStartTimeslot"));
                Long selectedEndTimeslot = Long.parseLong(metadata.get("selectedEndTimeslot"));
                List<Integer> dates = Arrays.stream(metadata.get("dates").split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                String selectedFieldsString = metadata.get("selectedFields");
                String totalFee = metadata.get("totalFee");
                String bookingPeriodString = metadata.get("bookingPeriod");

                System.out.println(dates);

                // Call the method to save booking
                saveBookingSummary(selectedStartTimeslot, selectedEndTimeslot, dates, selectedFieldsString, totalFee, bookingPeriodString);
            }
        }

        return ResponseEntity.ok("Received event");
    }

    public void saveBookingSummary(Long selectedStartTimeslot,
                                   Long selectedEndTimeslot,
                                   List<Integer> dates,
                                   String selectedFieldsString,
                                   String totalFee,
                                   String bookingPeriodString) {

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

        Booking booking = new Booking((double) Float.parseFloat(totalFee), customer, payment);

        bookingService.saveBooking(booking);
        reservedFieldTimeslotService.saveReservedFieldTimeslots(booking, fieldTimeslots, bookingPeriod);
    }
}
