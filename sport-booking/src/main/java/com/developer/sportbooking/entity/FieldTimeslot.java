package com.developer.sportbooking.entity;

import com.developer.sportbooking.enumType.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "FieldTimeslot")
@Table(name = "field_timeslot")
@Getter
@Setter
public class FieldTimeslot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_in_week")
    private DayOfWeek day;

    @Column(name = "price")
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ts_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Timeslot timeslot;

    // Booking - Field_Timeslot: one to many (child side)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "booking_id", referencedColumnName = "id")
//    private Booking booking;
    @OneToMany(
            mappedBy = "fieldTimeslot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<ReservedFieldTimeslot> reservedFieldTimeslots = new ArrayList<>();

    public FieldTimeslot() {}

    public FieldTimeslot(DayOfWeek day, Float price, Field field, Timeslot timeslot) {
        this.day = day;
        this.price = price;
        this.field = field;
        this.timeslot = timeslot;
    }

    public void addReservedFieldTimeslot(ReservedFieldTimeslot reservedFieldTimeslot) {
        reservedFieldTimeslots.add(reservedFieldTimeslot);
        reservedFieldTimeslot.setFieldTimeslot(this);
    }

    public void removeReservedFieldTimeslot(ReservedFieldTimeslot reservedFieldTimeslot) {
        reservedFieldTimeslots.remove(reservedFieldTimeslot);
        reservedFieldTimeslot.setFieldTimeslot(null);
    }

//Constructors, getters and setters removed for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        FieldTimeslot that = (FieldTimeslot) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
