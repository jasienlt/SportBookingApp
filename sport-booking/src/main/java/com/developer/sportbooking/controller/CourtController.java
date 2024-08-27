package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.AwsConfig;
import com.developer.sportbooking.config.CustomCustomerDetails;
import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.entity.Sportgroup;
import com.developer.sportbooking.enumConverter.RoleConverter;
import com.developer.sportbooking.enumType.Role;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.repository.SportgroupRepo;
import com.developer.sportbooking.service.CourtService;
import com.developer.sportbooking.service.CustomerService;
import com.developer.sportbooking.service.SportgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;

import java.sql.SQLException;
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
    private SportgroupService sportgroupService;
    @Autowired
    private CustomerService customerService;


    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute CustomerDto customerDto, Model model) {
        List<Sportgroup> listSportgroup = sportgroupService.findAllSportgroup();
        List<Customer> listAdmin = customerService.findByRole(Role.ADMIN);
        model.addAttribute("listSportgroup", listSportgroup);
        model.addAttribute("listAdmin", listAdmin);
        return "courtRegistration";
    }

    @GetMapping("/register?name={name}")
    @ResponseBody
    public void register(@PathVariable("name") String courtName, Model model) {
        Court existingCourt = courtService.findCourtByName(courtName);
        if (existingCourt == null) {
            System.out.println("YES");
        }
        System.out.println("NO");
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute CourtDto courtDto, Model model,
                                  @RequestParam("sportgroup") Long sportgroupId,
                                  @RequestParam("managedBy") Long managedById,
                                  @RequestParam("paymentImg") MultipartFile multipart) {

        if (courtService.findCourtByName(courtDto.getName()) != null) {
            String someMessage = "Court name needs to be unique! If you have registered, plese login.";
            model.addAttribute("someMessage", someMessage);
            return "redirect:/admin/court/register";
        }

        CourtDto courtDto1 = new CourtDto(courtDto.getName(), courtDto.getAddress(), courtDto.getPhone(), sportgroupService.findSportgroupById(sportgroupId), customerService.getCustomerById(managedById));
        courtService.saveCourt(courtDto1);
        Court court = courtService.findCourtByNameAndPhone(courtDto.getName(), courtDto.getPhone());

        try {
            String folderName = court.getId().toString();
            AwsConfig.uploadFile(folderName, multipart.getInputStream(),"court_url");

            // For each court, store separate folder in each category
            AwsConfig.createFolder(folderName,"payment_screenshots/rejected");
            AwsConfig.createFolder(folderName,"payment_screenshots/approved");
            AwsConfig.createFolder(folderName,"payment_screenshots/pending");
        }

        catch (Exception ex) {
            courtService.deleteCourtById(court.getId());
            String someMessage = "Error: " + ex.getMessage();
            model.addAttribute("someMessage1", someMessage);
            List<Sportgroup> listSportgroup = sportgroupService.findAllSportgroup();
            List<Customer> listAdmin = customerService.findByRole(Role.ADMIN);
            model.addAttribute("listSportgroup", listSportgroup);
            model.addAttribute("listAdmin", listAdmin);
            return "courtRegistration";
        }

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
