package com.developer.sportbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")


public class Booking {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "price", nullable = false)
    private Double price;

    // Customer - Booking: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // Payment - Booking: one to one
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", referencedColumnName = "id")
    private Payment payment;

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

    // Booking - FieldTimeslot: one to many (parent side)
    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Field_Timeslot> fieldTimeslots = new ArrayList<>();

    //Constructors, getters and setters removed for brevity

    public void addFieldTimeslot(Field_Timeslot fieldTimeslot) {
        fieldTimeslots.add(fieldTimeslot);
        fieldTimeslot.setBooking(this);
    }

    public void removeTimeslot(Field_Timeslot fieldTimeslot) {
        fieldTimeslots.remove(fieldTimeslot);
        fieldTimeslot.setBooking(null);
    }

}
