package com.developer.sportbooking.config;

import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
@Component
public class CustomCustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }
        return new CustomCustomerDetails(customer);
    }

}
