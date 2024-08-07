package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    // Booking - Payment: one to one (owning side)
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Booking booking;
    @Column(name="payment_evidence")
    private String paymentFile;

    @Column(name="payment_stts")
    private String paymentStatus;

    @Column(name="booking_id")
    private Long bookingId;

    public Payment(String paymentType) {
        this.paymentType = paymentType;
    }

    public Payment(String paymentType, String paymentStatus) {
        this.paymentType = paymentType;
        this.paymentFile = null;
        this.paymentStatus = paymentStatus;
        this.bookingId = null;
    }

    public Payment(String paymentType, String paymentFile, String paymentStatus) {
        this.paymentType = paymentType;
        this.paymentFile = paymentFile;
        this.paymentStatus = paymentStatus;
        this.bookingId = null;
    }

    public Payment(String paymentType, String paymentFile, String paymentStatus, Long bookingId) {
        this.paymentType = paymentType;
        this.paymentFile = paymentFile;
        this.paymentStatus = paymentStatus;
        this.bookingId = bookingId;
    }
}
