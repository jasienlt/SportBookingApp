package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepo extends JpaRepository<Court, Long>{
    @Query("SELECT u FROM Court u WHERE u.name = ?1")
    public Court findByName(String name);
}