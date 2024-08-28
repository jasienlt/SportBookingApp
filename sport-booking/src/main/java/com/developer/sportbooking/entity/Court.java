package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "court")

public class Court {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;


    // Sportgroup - Court: one to many (child side)
    @ToString.Exclude
    @Getter
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "sportgroup_id", referencedColumnName = "id")
    @JsonIgnore
    private Sportgroup sportgroup;

    // Customer/Admin - Court: one to many (child side)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "managed_by", referencedColumnName = "id")
    @JsonIgnore
    private Customer managedBy;

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
    @ToString.Exclude
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
    @ToString.Exclude
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
    @ToString.Exclude
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
    @ToString.Exclude
    @OneToMany(
            mappedBy = "court",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<CourtCustomer> customers = new ArrayList<CourtCustomer>();

    public Court() {
    }

    public Court(Long id, String name, String address, String phone, Sportgroup sportgroup) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sportgroup = sportgroup;
    }


    public Court(String name, String address, String phone, Sportgroup sportgroup, Customer managedBy) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sportgroup = sportgroup;
        this.managedBy = managedBy;
    }


}