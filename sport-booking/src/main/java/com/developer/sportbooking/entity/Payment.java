package com.developer.sportbooking.entity;

import com.developer.sportbooking.enumType.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")


public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    // Booking - Payment: one to one (owning side)
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Booking booking;

    @Column(name="payment_evidence")
    private String paymentFile;

    @Column(name="payment_stts")
    private PaymentStatus paymentStatus;

    @Column(name="booking_id")
    private Long bookingId;

    public Payment(String paymentType) {
        this.paymentType = paymentType;
    }

    public Payment(LocalDateTime createdDate, String paymentType, PaymentStatus paymentStatus) {
        this.createdDate = createdDate;
        this.paymentType = paymentType;
        this.paymentFile = null;
        this.paymentStatus = paymentStatus;
        this.bookingId = null;
    }

    public Payment(LocalDateTime createdDate, String paymentType, String paymentFile, PaymentStatus paymentStatus) {
        this.createdDate = createdDate;
        this.paymentType = paymentType;
        this.paymentFile = paymentFile;
        this.paymentStatus = paymentStatus;
        this.bookingId = null;
    }

    public Payment(LocalDateTime createdDate, String paymentType, String paymentFile, PaymentStatus paymentStatus, Long bookingId) {
        this.createdDate = createdDate;
        this.paymentType = paymentType;
        this.paymentFile = paymentFile;
        this.paymentStatus = paymentStatus;
        this.bookingId = bookingId;
    }
}
