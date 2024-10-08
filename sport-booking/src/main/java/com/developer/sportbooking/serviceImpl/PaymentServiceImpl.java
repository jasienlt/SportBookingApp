package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.PaymentStatus;
import com.developer.sportbooking.repository.PaymentRepo;
import com.developer.sportbooking.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {
    PaymentRepo paymentRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public List<Payment> findAllPayment() {
        return paymentRepo.findAll();
    }

    @Override
    public List<Payment> findPaymentByStatus(PaymentStatus paymentStatus) {
        return paymentRepo.findByPaymentStatus(paymentStatus);
    }

    @Override
    public Payment findPaymentById(Long id) {
        if(paymentRepo.findById(id).isPresent()) {
            return paymentRepo.findById(id).get();
        }

        return null;
    }

    @Override
    public void updatePaymentById(Payment payment, PaymentStatus paymentStatus) {
        paymentRepo.updatePaymentById(payment.getId(), paymentStatus);
    }

    @Override
    public Payment findPaymentByEvidence(String evidenceId) {
        return paymentRepo.findByPaymentFile(evidenceId);
    };

    @Override
    public List<Payment> hasNewPayment() {
        // Implement your logic to check for new data
        // For example, check for any new bookings since the last check
        LocalDateTime lastCheckedTime = LocalDateTime.now().minusMinutes(30);// Implement logic to store/retrieve last checked time
        List<Payment> newPayments = paymentRepo.findNewPaymentsSince(lastCheckedTime);
        return newPayments;
    }
}
