package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity(name = "Field_Timeslot")
@Table(name = "field_timeslot")
@Getter
@Setter
public class Field_Timeslot {
    @EmbeddedId
    public FieldTimeslotId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ts_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Timeslot timeslot;

    @Column(name = "price")
    private Float price;

    // Booking - Field_Timeslot: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    public Field_Timeslot() {}

    public Field_Timeslot(FieldTimeslotId fieldTimeslotId, Float price) {
        this.id = fieldTimeslotId;
        this.price = price;
    }

    //Constructors, getters and setters removed for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Field_Timeslot that = (Field_Timeslot) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
