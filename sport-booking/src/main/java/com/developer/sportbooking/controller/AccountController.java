package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.CustomCustomerDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AccountController {
    @GetMapping("/account")
    public String getAccount(@AuthenticationPrincipal CustomCustomerDetails customerDetails, Model model) {
        if (customerDetails == null) {
            return "redirect:/login";
        }

        return "account";
    }
}
