package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepo extends JpaRepository<Court, Long>{
    public Court findCourtByName(String name);
    @Query("SELECT u FROM Court u WHERE u.name = ?1")
    public Court findByName(String name);

    @Query("SELECT u.court FROM Field u WHERE u.id = ?1 ")
    Court findByField(Long fieldId);

    @Query("SELECT u FROM Court u WHERE u.managedBy = ?1 ")
    List<Court> findByManagedBy(Long adminId);

    @Query("SELECT u FROM Court u WHERE u.name = :courtName AND u.phone = :phone")
    Court findByNameAndPhone(String courtName, String phone);
}