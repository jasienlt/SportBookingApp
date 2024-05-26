package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "court")


public class Court {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;


    // Sportgroup - Court: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "sportgroup_id", referencedColumnName = "id")
    @JsonIgnore
    private Sportgroup sportgroup;

    //Constructors, getters and setters removed for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Court )) return false;
        return id != null && id.equals(((Court) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // Question: should we equal by objectID or objectNaturalId (name, starttime, etc.)? -> Performance?

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Tag tag = (Tag) o;
//        return Objects.equals(name, tag.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }

    // Court - Product: one to many (parent side)
    @OneToMany(
            mappedBy = "court",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products = new ArrayList<Product>();


    public void addProduct(Product product) {
        products.add(product);
        product.setCourt(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setCourt(null);
    }

    // Court - Field: one to many (parent side)
    @OneToMany(
            mappedBy = "court",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Field> fields = new ArrayList<Field>();

    public void addField(Field field) {
        fields.add(field);
        field.setCourt(this);
    }

    public void removeField(Field field) {
        fields.remove(field);
        field.setCourt(null);
    }

    // Court - Timeslot: one to many (parent side)
    @OneToMany(
            mappedBy = "court",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Timeslot> timeslots = new ArrayList<Timeslot>();

    public void addTimeslot(Timeslot timeslot) {
        timeslots.add(timeslot);
        timeslot.setCourt(this);
    }

    public void removeTimeslot(Timeslot timeslot) {
        timeslots.remove(timeslot);
        timeslot.setCourt(null);
    }

    // Court - Customer: Many to many
    @OneToMany(
            mappedBy = "court",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Court_Customer> customers = new ArrayList<Court_Customer>();

    public Court() {
    }

    public Court(Integer id, String name, String address, String phone, Sportgroup sportgroup) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sportgroup = sportgroup;
    }


}