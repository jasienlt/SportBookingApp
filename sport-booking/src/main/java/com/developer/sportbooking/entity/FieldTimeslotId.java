package com.developer.sportbooking.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class FieldTimeslotId implements Serializable {

    private Integer fieldId;
    private Integer timeslotId;

    public FieldTimeslotId() {
    }

    public FieldTimeslotId(Integer fieldId, Integer timeslotId) {
        super();
        this.fieldId = fieldId;
        this.timeslotId = timeslotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldId, timeslotId);
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
        return Objects.equals(getFieldId(), other.getFieldId()) && Objects.equals(getTimeslotId(), other.getTimeslotId());
    }
}
