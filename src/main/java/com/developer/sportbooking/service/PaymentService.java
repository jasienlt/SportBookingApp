package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.entity.Product;

import java.util.List;

public interface PaymentService {
    Payment savePayment(Payment payment);

    List<Payment> findAllPayment();

    Payment findPaymentById(Long id);

    Payment findPaymentByBookingId(Long id);

    Payment updatePaymentById(Payment payment, Long id);

    void deletePaymentById(Long id);
}
