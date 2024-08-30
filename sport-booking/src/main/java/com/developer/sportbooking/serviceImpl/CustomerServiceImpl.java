package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.enumConverter.RoleConverter;
import com.developer.sportbooking.enumType.Role;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        super();
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto.getFirstName(),customerDto.getLastName(),customerDto.getPhone(),customerDto.getEmail(), customerDto.getPassword());
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

    @Override
    public Customer getCustomerById(Long id) {
        if (customerRepo.findById(id).isPresent())
            return customerRepo.findById(id).get();
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepo.findCustomerByEmail(email);
    }

    @Override
    public List<Customer> findByRole(Role role) {
        return customerRepo.findAllByRole(role);
    }

}
