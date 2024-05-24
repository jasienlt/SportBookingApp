package com.developer.sportbooking.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class CourtCustomerId implements Serializable {

    private Integer courtId;
    private Integer customerId;

    public CourtCustomerId() {
    }

    public CourtCustomerId(Integer courtId, Integer customerId) {
        super();
        this.courtId = courtId;
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courtId, customerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CourtCustomerId other = (CourtCustomerId) obj;
        return Objects.equals(getCourtId(), other.getCourtId()) && Objects.equals(getCustomerId(), other.getCustomerId());
    }
}