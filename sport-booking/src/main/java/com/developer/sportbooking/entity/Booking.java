package com.developer.sportbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;

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
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    // Customer - Booking: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking )) return false;
        return id != null && id.equals(((Booking) o).getId());
    }

    // Booking - FieldTimeslot: one to many (parent side)
    @OneToMany(
            mappedBy = "field_timeslot",
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
