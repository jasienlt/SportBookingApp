package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.CustomCustomerDetails;
import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Controller

public class CustomerController {


    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currentCustomer", new Customer());
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal CustomCustomerDetails customerDetails) {
        model.addAttribute("customerDetail", customerDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute CustomerDto customerDto) {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("currentCustomer") CustomerDto customerDto, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(customerDto.getPassword());
        customerDto.setPassword(encodedPassword);
        Customer customer = customerService.validateCustomer(customerDto.getEmail(),customerDto.getPassword());
        System.out.println("ABC");
        if (Objects.isNull(customer)) {
            // Return an error--?
            return "registration";
        } else {
            customerService.saveCustomer(customerDto);
            model.addAttribute("currentCustomer", customer);
            return "home";
        }
    }

    @GetMapping("/register")
    public String register(@ModelAttribute CustomerDto customerDto) {
        return "registration";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute CustomerDto customerDto, Model model) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(customerDto.getPassword());
        customerDto.setPassword(encodedPassword);
//        if (customerService.isCustomerExist(customerDto.getEmail())) {
//            model.addAttribute("CustomerExist", 1);
//            return "registration";
//        }
        customerService.saveCustomer(customerDto);
        model.addAttribute("firstName", customerDto.getFirstName());
        return "login";

    }
}
