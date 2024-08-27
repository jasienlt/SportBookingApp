package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepo extends JpaRepository<Field, Long>{
    List<Field> findAllByCourt_Id(Long courtId);
}