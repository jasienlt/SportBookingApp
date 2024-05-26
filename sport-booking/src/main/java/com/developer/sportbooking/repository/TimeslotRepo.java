package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepo extends JpaRepository<Timeslot, Integer>{

}