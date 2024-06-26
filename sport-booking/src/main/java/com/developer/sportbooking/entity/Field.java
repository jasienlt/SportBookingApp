package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "field")

public class Field {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sport_type")
    private String sportType;

    // Court - Field: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", referencedColumnName = "id")
    @JsonIgnore
    private Court court;

    public Field() {}

    public Field(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;
        return id != null && id.equals(((Field) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // Field - Timeslot: Many to many
    @OneToMany(mappedBy = "field",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    @JsonIgnore
    private List<FieldTimeslot> timeslots = new ArrayList<>();

//    public void addTimeslot(Timeslot timeslot) {
//        Field_Timeslot fieldTimeslot = new Field_Timeslot(this, timeslot);
//        timeslots.add(fieldTimeslot);
//        timeslot.getFields().add(fieldTimeslot);
//    }

    public void removeTimeslot(Timeslot timeslot) {
        for (Iterator<FieldTimeslot> iterator = timeslots.iterator();
             iterator.hasNext(); ) {
            FieldTimeslot fieldTimeslot = iterator.next();

            if (fieldTimeslot.getField().equals(this) &&
                    fieldTimeslot.getTimeslot().equals(timeslot)) {
                iterator.remove();
                fieldTimeslot.getTimeslot().getFields().remove(fieldTimeslot);
                fieldTimeslot.setField(null);
                fieldTimeslot.setTimeslot(null);
            }
        }
    }
}
