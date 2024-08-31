package com.developer.sportbooking.config;

import com.developer.sportbooking.config.CustomCustomerDetails;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomCustomerDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    public CustomCustomerDetailsService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public CustomCustomerDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomCustomerDetails(customer);
    }
}

