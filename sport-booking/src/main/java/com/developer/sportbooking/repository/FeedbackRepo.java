package com.developer.sportbooking.persistence.repository;

import com.developer.sportbooking.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    @Query("SELECT u FROM Feedback u WHERE u.court.id = :courtId")
    public List<Feedback> findByCourtId(@Param("courtId") Long courtId);
}
