package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.BookingStatus;
import com.developer.sportbooking.enumType.PaymentStatus;
import com.developer.sportbooking.service.*;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class StripeWebhookController {

    private final PaymentService paymentService;
    @Value("${END_POINT_SECRET}")
    private String endpointSecret;  // Replace with your webhook secret

    private final BookingService bookingService;

    private final ReservedFieldTimeslotService reservedFieldTimeslotService;

    @Autowired
    public StripeWebhookController(BookingService bookingService, ReservedFieldTimeslotService reservedFieldTimeslotService, @Qualifier("paymentService") PaymentService paymentService) {
        this.bookingService = bookingService;
        this.reservedFieldTimeslotService = reservedFieldTimeslotService;
        this.paymentService = paymentService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeEvent(@RequestBody String payload,
                                                    @RequestHeader("Stripe-Signature") String sigHeader) throws StripeException {
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
                Booking completedBooking = bookingService.getBookingBySessionId(session.getId());
                Payment completedPayment = paymentService.findPaymentByEvidence(session.getId());
                if(completedBooking != null) {
                    completedBooking.setStatus(BookingStatus.COMPLETED);
                    bookingService.saveBooking(completedBooking);

                    completedPayment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
                    paymentService.savePayment(completedPayment);
                }
            }
        }
        else if ("checkout.session.expired".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
            if (session != null) {
                Booking completedBooking = bookingService.getBookingBySessionId(session.getId());
                Payment completedPayment = paymentService.findPaymentByEvidence(session.getId());

                if(completedBooking != null) {
                    completedBooking.setStatus(BookingStatus.CANCELED);
                    bookingService.saveBooking(completedBooking);
                    reservedFieldTimeslotService.removeReservedFieldByBooking(completedBooking);

                    completedPayment.setPaymentStatus(PaymentStatus.CANCELED);
                    paymentService.savePayment(completedPayment);
                }
            }
        }

        return ResponseEntity.ok("Received event");
    }
}
