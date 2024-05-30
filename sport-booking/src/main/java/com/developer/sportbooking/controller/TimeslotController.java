package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeslotController {
    @Autowired
    TimeslotService timeslotService;

    @GetMapping("/timeslots")
    public List<Timeslot> fetchAllTimeslots() {
        return timeslotService.findAllTimeslot();
    }
}
