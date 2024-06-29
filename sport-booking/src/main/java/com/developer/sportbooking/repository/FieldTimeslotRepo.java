package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.entity.FieldTimeslotId;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.enumType.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldTimeslotRepo extends JpaRepository<FieldTimeslot, Long> {
    FieldTimeslot findFieldTimeslotByFieldAndTimeslotAndDay(Field field, Timeslot timeslot, DayOfWeek day);
}
