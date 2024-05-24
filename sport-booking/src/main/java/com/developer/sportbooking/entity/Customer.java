package com.developer.sportbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "customer")


public class Customer {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    // Customer - Booking: one to many (parent side)
    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Booking> bookings = new ArrayList<>();

    public void addTimeslot(Booking booking) {
        bookings.add(booking);
        booking.setCustomer(this);
    }

    public void removeTimeslot(Booking booking) {
        bookings.remove(booking);
        booking.setCustomer(null);
    }

    // Court - Customer: Many to many
    @OneToMany(
            mappedBy = "court",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Court_Customer> courts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    //Getters and setters omitted for brevity

    public void addCourt(Court court) {
        Court_Customer courtCustomer = new Court_Customer(court, this);
        courts.add(courtCustomer);
        court.getCustomers().add(courtCustomer);
    }

    public void removeCourt(Court court) {
        for (Iterator<Court_Customer> iterator = courts.iterator();
             iterator.hasNext(); ) {
            Court_Customer courtCustomer = iterator.next();

            if (courtCustomer.getCustomer().equals(this) &&
                    courtCustomer.getCourt().equals(court)) {
                iterator.remove();
                courtCustomer.getCustomer().getCourts().remove(courtCustomer);
                courtCustomer.setCourt(null);
                courtCustomer.setCustomer(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        return id != null && id.equals(((Customer) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
