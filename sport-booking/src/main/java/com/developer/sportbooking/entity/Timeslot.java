package com.developer.sportbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.sql.Time;

import java.util.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "timeslot")
@NaturalIdCache
//@org.hibernate.annotations.Cache(
//        usage = CacheConcurrencyStrategy.READ_WRITE
//)
public class Timeslot {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NaturalId
    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    public Timeslot() {}

    public Timeslot(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Court - Timeslot: one to many (child side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", referencedColumnName = "id")
    @JsonIgnore
    private Court court;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timeslot tag = (Timeslot) o;
        return Objects.equals(startTime, tag.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime);
    }

    // Field - Timeslot: Many to many
    @OneToMany(mappedBy = "timeslot",
                cascade = CascadeType.ALL,
                orphanRemoval = true)

    // Can seek to change into HashMap - holding key (field,timeslot) - value as list of (startTime, endTime)
    @JsonIgnore
    private List<FieldTimeslot> fields = new ArrayList<>();
}
