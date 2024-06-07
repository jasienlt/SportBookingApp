package com.developer.sportbooking.config;

import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
public class CustomCustomerDetailsService implements UserDetailsService {

    private CustomerRepo customerRepo;

    public CustomCustomerDetailsService(CustomerRepo customerRepo) {
        super();
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(email);
        if (Objects.isNull(customer)) {
            throw new UsernameNotFoundException("Email or Password not found");
        }
        return new CustomCustomerDetails(customer.getFirstName(),customer.getLastName(),customer.getPhone(),customer.getEmail(),customer.getPassword(), authorities());
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

}
