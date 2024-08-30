package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PaymentRepo extends JpaRepository<Payment, Long>{
    List<Payment> findByPaymentStatus(PaymentStatus status);

    @Modifying
    @Query("UPDATE Payment p set p.paymentStatus = :status where p.id = :paymentId")
    void updatePaymentById(Long paymentId, PaymentStatus status);
}