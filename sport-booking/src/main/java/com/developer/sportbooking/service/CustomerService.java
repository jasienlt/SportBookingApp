package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> findAllCustomer();

    List<Customer> findCustomerByCourt(Integer id);

    Customer findCustomerById(Integer id);

    Customer updateCustomerById(Customer customer, Integer id);

    void deleteCustomerById(Integer id);
}
