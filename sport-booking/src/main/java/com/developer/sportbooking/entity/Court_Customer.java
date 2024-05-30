package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity(name = "Court_Customer")
@Table(name = "court_customer")
@Getter
@Setter
public class Court_Customer {
    @EmbeddedId
    private CourtCustomerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Court court;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "loyalty_point")
    private Float loyaltyPoint;

    public Court_Customer() {}

    public Court_Customer(Court court, Customer customer) {
        this.court = court;
        this.customer = customer;
        this.id = new CourtCustomerId(court.getId(), customer.getId());
    }


    //Constructors, getters and setters removed for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Court_Customer that = (Court_Customer) o;
        return Objects.equals(court, that.court) &&
                Objects.equals(customer, that.customer);
    }
    @Override
    public int hashCode() {
        return Objects.hash(court, customer);
    }
}