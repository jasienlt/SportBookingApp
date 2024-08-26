package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.enumType.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldTimeslotRepo extends JpaRepository<FieldTimeslot, Long> {
    FieldTimeslot findFieldTimeslotByFieldAndTimeslotAndDay(Field field, Timeslot timeslot, DayOfWeek day);

}
