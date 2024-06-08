package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        super();
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto.getFirstName(),customerDto.getLastName(),customerDto.getPhone(),customerDto.getEmail(), passwordEncoder.encode(customerDto.getPassword()));
        return customerRepo.save(customer);
    }

    @Override
    public Customer validateCustomer(String email, String password) {
        Customer currentCustomer = customerRepo.findByEmail(email);
        if (Objects.equals(currentCustomer.getPassword(), password)) {
            return currentCustomer;
        } else {
            return null;
        }
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepo.findAll();
    }


    @Override
    public List<Customer> findByCourt(Long id) {
        return List.of();
    }



}
