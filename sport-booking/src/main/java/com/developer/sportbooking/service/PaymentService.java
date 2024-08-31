package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.entity.Product;
import com.developer.sportbooking.enumType.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Payment savePayment(Payment payment);

    List<Payment> findAllPayment();

    List<Payment> findPaymentByStatus(PaymentStatus paymentStatus);

    Payment findPaymentById(Long id);

    void updatePaymentById(Payment payment, PaymentStatus paymentStatus);

    Payment findPaymentByEvidence(String evidenceId);

//    Payment findPaymentByBookingId(Long id);
//
//    Payment updatePaymentById(Payment payment, Long id);
//
//    void deletePaymentById(Long id);
}
