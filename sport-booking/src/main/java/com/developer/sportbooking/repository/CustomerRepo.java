package com.developer.sportbooking.repository;

import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.enumType.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

    @Query("SELECT u FROM Customer u WHERE u.email = ?1")
    public Customer findByEmail(String email);

    Customer findCustomerByEmail(String email);

    List<Customer> findAllByRole(Role role);
}