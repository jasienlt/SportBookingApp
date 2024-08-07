package com.developer.sportbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class FieldTimeslotId implements Serializable {
    @Column(name = "field_id")
    public Long fieldId;
    @Column(name = "ts_id")
    public Long timeslotId;
    @Column(name = "day_in_week")
    public Integer dayId;

    public FieldTimeslotId() {
    }

    public FieldTimeslotId(Long fieldId, Long timeslotId, Integer dayId) {
        this.fieldId = fieldId;
        this.timeslotId = timeslotId;
        this.dayId = dayId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(fieldId, timeslotId, dayId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldTimeslotId other = (FieldTimeslotId) obj;
        return fieldId.equals(other.fieldId) && timeslotId.equals(other.timeslotId) && dayId.equals(other.dayId);
    }
}
