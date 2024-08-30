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
    public String login(@AuthenticationPrincipal CustomCustomerDetails customerDetails) {
        if (customerDetails != null) {
            return "redirect:/homepage";
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("currentCustomer") CustomerDto customerDto, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(customerDto.getPassword());
        customerDto.setPassword(encodedPassword);
        Customer customer = customerService.validateCustomer(customerDto.getEmail(),customerDto.getPassword());
        System.out.println(encodedPassword);
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
        // Check if password and confirm password match
        if (!customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
            model.addAttribute("passwordMismatch", true);
            return "registration"; // Return to the registration page with an error message
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(customerDto.getPassword());
        customerDto.setPassword(encodedPassword);

        // Additional logic to check if the email is already taken
//        if (customerService.isCustomerExist(customerDto.getEmail())) {
//            model.addAttribute("CustomerExist", true);
//            return "registration"; // Return to the registration page with an error message
//        }

        customerService.saveCustomer(customerDto);
        return "redirect:/login"; // Redirect to the login page after successful registration
    }

}
