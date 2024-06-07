package com.developer.sportbooking.controller;

import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Controller

public class CustomerController {

    @Autowired
    private UserDetailsService customerDetailsService;

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        UserDetails customerDetails = customerDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("customerdetail", customerDetails);
        return "home.jsp";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute CustomerDto customerDto) {
        return "login.jsp";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute CustomerDto customerDto) {
        return "registration.jsp";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute CustomerDto customerDto, Model model) {
        Customer customer = customerService.validateCustomer(customerDto.getEmail(), customerDto.getPassword());
        if (Objects.nonNull(customer)) {
            model.addAttribute("CustomerExist", customer);
            model.addAttribute("firstName", customerDto.getFirstName());
            return "registration.jsp";
        } else {
            customerService.saveCustomer(customerDto);
            return "home.jsp";
        }
    }
}
