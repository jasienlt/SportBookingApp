package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserved_field_timeslot")
public class ReservedFieldTimeslot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @JsonIgnore
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fts_id", referencedColumnName = "id")
    private FieldTimeslot fieldTimeslot;

    @Column(name = "booking_date")
    Date bookingDate;

    public ReservedFieldTimeslot(Booking booking, FieldTimeslot fieldTimeslot, Date bookingDate) {
        this.booking = booking;
        this.fieldTimeslot = fieldTimeslot;
        this.bookingDate = bookingDate;
    }
}
