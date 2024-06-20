package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.CourtCustomerId;
import com.developer.sportbooking.entity.CourtCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtCustomerRepo extends JpaRepository<CourtCustomer, CourtCustomerId> {
}
