package com.developer.sportbooking.service;

import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    Customer saveCustomer(CustomerDto customerDto);

    Customer validateCustomer(String email, String password);

    List<Customer> findAllCustomer();

    List<Customer> findByCourt(Long id);

}
