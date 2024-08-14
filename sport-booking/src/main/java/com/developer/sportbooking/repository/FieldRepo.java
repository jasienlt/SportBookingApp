package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepo extends JpaRepository<Field, Long>{

}