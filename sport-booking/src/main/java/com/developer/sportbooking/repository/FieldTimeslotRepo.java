package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.FieldTimeslotId;
import com.developer.sportbooking.entity.FieldTimeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldTimeslotRepo extends JpaRepository<FieldTimeslot, FieldTimeslotId> {
}
