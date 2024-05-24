package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.entity.Sportgroup;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.repository.SportgroupRepo;
import com.developer.sportbooking.service.CustomerService;
import com.developer.sportbooking.service.SportgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return (List<Customer>) customerRepo.findAll();
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return null;
    }

    @Override
    public Customer updateCustomerById(Customer customer, Integer id) {
        Customer customer1 = customerRepo.findById(id).get();
        if (Objects.nonNull(customer.getEmail())
                && !"".equalsIgnoreCase(
                customer.getEmail())) {
            customer1.setEmail(customer.getEmail());
        }
        return customerRepo.save(customer1);

    }

    @Override
    public void deleteCustomerById(Integer id) {
        customerRepo.deleteById(id);
    }
}
