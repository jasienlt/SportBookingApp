package com.developer.sportbooking.repository;

import com.developer.sportbooking.chartDto.BookingCount;
import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long>{
    Booking findBookingBySessionId(String sessionId);

    @Query("SELECT new com.developer.sportbooking.chartDto.BookingCount(c.createdDate, c.status, count(*)) "
            + "FROM Booking as c WHERE c.createdDate >= :cutoffDate "
            + "group by c.createdDate, c.status order by c.createdDate, c.status")
    List<BookingCount> findByStatus(Date cutoffDate);
}