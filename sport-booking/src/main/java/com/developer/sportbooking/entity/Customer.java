package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "customer")


public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @NaturalId(mutable = true)
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    // Security config
//    private boolean isEnabled = false;

//    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable (name = "customer_roles",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private List<Role> roles;

    // Customer - Booking: one to many (parent side)
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setCustomer(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setCustomer(null);
    }

    // Court - Customer: Many to many
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Court_Customer> courts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
//        this.roles = roles;
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
