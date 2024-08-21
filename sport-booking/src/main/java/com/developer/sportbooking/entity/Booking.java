package com.developer.sportbooking.entity;

import com.developer.sportbooking.enumType.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "price", nullable = false)
    private Double price;

    // Customer - Booking: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    // Payment - Booking: one to one
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_id", referencedColumnName = "id")
    private Payment payment;

    // Booking - FieldTimeslot: one to many (parent side)
    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<ReservedFieldTimeslot> reservedFieldTimeslots = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Column(name = "stripe_session_id", nullable = false)
    private String sessionId;

    public Booking(Double price, Customer customer, Payment payment) {
        this.price = price;
        this.customer = customer;
        this.payment = payment;
    }

    public Booking(Date createdDate, Double price, Customer customer, Payment payment, BookingStatus status, String sessionId) {
        this.createdDate = createdDate;
        this.price = price;
        this.customer = customer;
        this.payment = payment;
        this.status = status;
        this.sessionId = sessionId;
    }

    public void addReservedFieldTimeslot(ReservedFieldTimeslot reservedFieldTimeslot) {
        reservedFieldTimeslots.add(reservedFieldTimeslot);
        reservedFieldTimeslot.setBooking(this);
    }

    public void removeReservedFieldTimeslot(ReservedFieldTimeslot reservedFieldTimeslot) {
        reservedFieldTimeslots.remove(reservedFieldTimeslot);
        reservedFieldTimeslot.setBooking(null);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking )) return false;
        return id != null && id.equals(((Booking) o).getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    //Constructors, getters and setters removed for brevity
}
