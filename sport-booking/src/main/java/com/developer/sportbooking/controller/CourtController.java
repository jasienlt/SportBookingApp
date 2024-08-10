package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.AwsConfig;
import com.developer.sportbooking.config.CustomCustomerDetails;
import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.entity.Sportgroup;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.repository.SportgroupRepo;
import com.developer.sportbooking.service.CourtService;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/court")
public class CourtController {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private CourtService courtService;
    @Autowired
    private SportgroupRepo sportgroupRepo;


    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute CustomerDto customerDto, Model model) {
        List<Sportgroup> listSportgroup = sportgroupRepo.findAll();
        model.addAttribute("listSportgroup", listSportgroup);
        return "courtRegistration";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute CourtDto courtDto, Model model, @RequestParam("file") MultipartFile multipart) {

        if (courtService.findCourtByName(courtDto.getName()) != null) {
            String someMessage = "Court name needs to be unique! Please change to a different name!";
            model.addAttribute("someMessage", someMessage);
            return "courtRegistration";
        }


        try {
            String folderName = courtDto.getId().toString();
            AwsConfig.uploadFile(folderName, multipart.getInputStream(),"court_url");

            // For each court, store separate folder in each category
            AwsConfig.createFolder(folderName,"payment_screenshots/rejected");
            AwsConfig.createFolder(folderName,"payment_screenshots/approved");
            AwsConfig.createFolder(folderName,"payment_screenshots/pending");

            String someMessage = "Your file and folder has been uploaded successfully!";
            model.addAttribute("someMessage", someMessage);
        }
        catch (Exception ex) {
            String someMessage = "Error uploading file: " + ex.getMessage();
            model.addAttribute("someMessage", someMessage);
            return "courtRegistration";
        }


        courtService.saveCourt(courtDto);

        String someMessage = "Registered new court successfully.";
        model.addAttribute("someMessage", someMessage);
        return "dashboardAdmin";

    }


//    @GetMapping("user-page")
//    public String userPage (Model model, Principal principal) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("customer", userDetails);
//        return "user";
//    }

}
