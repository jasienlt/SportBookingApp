package com.developer.sportbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developer.sportbooking.entity.Sportgroup;

@Repository
public interface SportgroupRepo extends JpaRepository<Sportgroup, Integer>{

}