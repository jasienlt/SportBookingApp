package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.repository.PaymentRepo;
import com.developer.sportbooking.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService {
    PaymentRepo paymentRepo;

    public PaymentServiceImp(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }
    @Override
    public Payment findPaymentById(Long id) {
        if(paymentRepo.findById(id).isPresent()) {
            return paymentRepo.findById(id).get();
        }

        return null;
    }
}
