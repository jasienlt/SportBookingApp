package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.entity.Product;

import java.util.List;

public interface PaymentService {
    Payment savePayment(Payment payment);

    List<Payment> findAllPayment();

    Payment findPaymentById(Integer id);

    Payment findPaymentByBookingId(Integer id);

    Payment updatePaymentById(Payment payment, Integer id);

    void deletePaymentById(Integer id);
}
