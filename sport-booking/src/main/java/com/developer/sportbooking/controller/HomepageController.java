package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomepageController {
    @Value("${GG_MAP_API}")
    private String googleMapApi;
    CourtService courtService;

    @Autowired
    HomepageController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/homepage")
    public String getHomepage(Model model) {
        ArrayList<Court> courts = courtService.findAllCourt();
        model.addAttribute("courts", courts);
        model.addAttribute("googleMapApi", googleMapApi);
        return "homepage";
    }

    @GetMapping("/getCourtDetails")
    public String getClubDetails(Model model, @RequestParam String courtId) {
        Court court = courtService.findCourtById(Long.valueOf(courtId));
        model.addAttribute("court", court);
        return "getCourtDetails";
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "success";
    }
}
