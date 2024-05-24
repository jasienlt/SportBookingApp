package com.developer.sportbooking.entity;

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
    private FieldTimeslotId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("fieldId")
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("timeslotId")
    private Timeslot timeslot;

    @Column(name = "price")
    private Float price;

    @Column(name = "day_in_week")
    private Integer dateInWeek;

    public Field_Timeslot() {}

    public Field_Timeslot(Field field, Timeslot timeslot) {
        this.field = field;
        this.timeslot = timeslot;
        this.id = new FieldTimeslotId(field.getId(), timeslot.getId());
    }

    // Booking - Field_Timeslot: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    private Booking booking;


    //Constructors, getters and setters removed for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Field_Timeslot that = (Field_Timeslot) o;
        return Objects.equals(field, that.field) &&
                Objects.equals(timeslot, that.timeslot);
    }
    @Override
    public int hashCode() {
        return Objects.hash(field, timeslot);
    }

}
