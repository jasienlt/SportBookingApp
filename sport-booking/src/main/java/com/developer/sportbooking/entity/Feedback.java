package com.developer.sportbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "feedback")
    private String feedback;

    // Customer - Feedback: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // Customer - Feedback: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", referencedColumnName = "id")
    private Court court;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking )) return false;
        return id != null && id.equals(((Booking) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public Feedback(Date date, Integer rating, String feedback, Customer customer, Court court){
        this.date= date;
        this.rating =rating;
        this.feedback = feedback;
        this.customer = customer;
        this.court = court;
    }

}
