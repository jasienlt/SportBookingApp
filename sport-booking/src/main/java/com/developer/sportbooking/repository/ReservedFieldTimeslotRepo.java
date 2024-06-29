package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.ReservedFieldTimeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservedFieldTimeslotRepo extends JpaRepository<ReservedFieldTimeslot, Long> {
    public List<ReservedFieldTimeslot> findReservedFieldTimeslotByBookingDateBetween(Date firstDate, Date lastDate);
}
