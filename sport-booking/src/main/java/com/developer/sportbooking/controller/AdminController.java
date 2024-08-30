package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.CustomCustomerDetails;
import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.enumType.Role;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private CustomerService customerService;


    public AdminController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal CustomCustomerDetails customerDetails) {
        model.addAttribute("customerdetail", customerDetails);
        if(customerDetails.getAuthorities().contains("ADMIN")) {
            return "approvePayment";
        } else if(customerDetails.getAuthorities().contains("OWNER")) {}
        return "dashboardOwner";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute CustomerDto customerDto) {
        return "courtLogin";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("currentAdmin") CustomerDto customerDto, Model model) {

        Customer customer = customerService.validateCustomer(customerDto.getEmail(),customerDto.getPassword());
        if (Objects.isNull(customer)) {
            String someMessage = "Customer not exist. Would you like to register?";
            model.addAttribute("someMessage", someMessage);
            return "courtLogin";
        } else if (customer.getRole() != Role.CUSTOMER){

            customerService.saveCustomer(customerDto);
            model.addAttribute("currentAdmin", customer);
            return "redirect:/dashboardAdmin";
        } else {
            String someMessage = "This page is for admin. Would you like to login as customer?";
            model.addAttribute("someMessage", someMessage);
            return "customerLogin";
        }

    }


//    @GetMapping("admin-page")
//    public String adminPage (Model model, Principal principal) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("customer", userDetails);
//        return "admin";
//    }

}

