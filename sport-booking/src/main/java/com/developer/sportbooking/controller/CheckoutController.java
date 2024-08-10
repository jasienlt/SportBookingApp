package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.PaymentStatus;
import com.developer.sportbooking.service.BookingService;
import com.developer.sportbooking.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CheckoutController {
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private static final String YOUR_DOMAIN = "http://localhost:8080";

    public CheckoutController(BookingService bookingService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(@RequestParam Long selectedStartTimeslot,
                                        @RequestParam Long selectedEndTimeslot,
                                        @RequestParam(name = "date") List<Integer> dates,
                                        @RequestParam(name = "selectedFields") String selectedFieldsString,
                                        @RequestParam(name = "totalFee") String totalFee,
                                        @RequestParam(name = "bookingPeriod") String bookingPeriodString
                                        ) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/success")
                .setCancelUrl(YOUR_DOMAIN + "/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("AUD")
                                                .setUnitAmount((long) Float.parseFloat(totalFee))
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Booking Slot")
                                                                .build())
                                                .build())
                                .build())
                .build();

        Session session = Session.create(params);

        bookingService.saveBookingSummary(selectedStartTimeslot, selectedEndTimeslot, dates, selectedFieldsString, totalFee, bookingPeriodString, session.getId());

        return "redirect:" + session.getUrl();
    }
}
