package com.developer.sportbooking.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sportgroup")


public class Sportgroup {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Sportgroup - Court: one to many (parent side)
    @OneToMany(
            mappedBy = "sportgroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Court> courts = new ArrayList<Court>();

    //Constructors, getters and setters removed for brevity

    public void addCourt(Court court) {
        courts.add(court);
        court.setSportgroup(this);
    }

    public void removeCourt(Court court) {
        courts.remove(court);
        court.setSportgroup(null);
    }
}
