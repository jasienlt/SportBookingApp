package com.developer.sportbooking.controller;

import com.developer.sportbooking.dto.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CheckoutController {
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;
    private static final String YOUR_DOMAIN = "http://localhost:8080";

    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(@RequestParam Long selectedStartTimeslot,
                                        @RequestParam Long selectedEndTimeslot,
                                        @RequestParam(name = "date") List<Integer> dates,
                                        @RequestParam(name = "selectedFields") String selectedFieldsString,
                                        @RequestParam(name = "totalFee") String totalFee,
                                        @RequestParam(name = "bookingPeriod") String bookingPeriodString
                                        ) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        String selectedStartTimeslotStr = selectedStartTimeslot.toString();
        String selectedEndTimeslotStr = selectedEndTimeslot.toString();
        String datesStr = dates.stream().map(String::valueOf).collect(Collectors.joining(","));

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/success")
                .setCancelUrl(YOUR_DOMAIN + "/cancel")
                .putMetadata("selectedStartTimeslot", selectedStartTimeslotStr)
                .putMetadata("selectedEndTimeslot", selectedEndTimeslotStr)
                .putMetadata("dates", datesStr)
                .putMetadata("selectedFields", selectedFieldsString)
                .putMetadata("totalFee", totalFee)
                .putMetadata("bookingPeriod", bookingPeriodString)
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

        return "redirect:" + session.getUrl();
    }
}
