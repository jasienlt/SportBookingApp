package com.developer.sportbooking.entity;

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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    // Booking - Payment: one to one (owning side)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Booking booking;

    public Payment(String paymentType) {
        this.paymentType = paymentType;
    }
}
