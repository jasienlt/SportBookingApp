package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    TimeslotService timeslotService;

    @GetMapping("/booking")
    public String booking(Model model) {
        List<Timeslot> timeslots = timeslotService.findAllTimeslot();
        String name = "Vuong";

        model.addAttribute("timeslots", timeslots);
        model.addAttribute("name", name);

        return "booking";
    }

    @GetMapping("/homepage")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("name", "hello");
        return "demo";
    }
}
