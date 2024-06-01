package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.service.FieldService;
import com.developer.sportbooking.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    TimeslotService timeslotService;
    @Autowired
    FieldService fieldService;

    @GetMapping("/booking")
    public String booking(Model model) {
        List<Timeslot> timeslots = timeslotService.findAllTimeslot();
        List<Field> fields = fieldService.findAllField();
        List<String> timeslotStartTime = new ArrayList<>();

        for(Timeslot timeslot : timeslots) {
            timeslotStartTime.add(timeslot.getStartTime().toString().substring(0, timeslot.getStartTime().toString().length() - 3));
        }

        model.addAttribute("timeslots", timeslots);
        model.addAttribute("fields", fields);
        model.addAttribute("timeslotStartTime", timeslotStartTime);

        return "booking";
    }

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

    @PostMapping("/booking_summary")
    public String bookingSummary(@RequestParam Integer selectedStartTimeslot,
                                 @RequestParam Integer selectedEndTimeslot,
                                 @RequestParam(name = "date") List<Integer> dates,
                                 @RequestParam(name = "selectedFields") String selectedFieldsString,
                                 Model model) {
        List<Integer> selectedDates = new ArrayList<>();
        List<String> selectedFields = Arrays.asList(selectedFieldsString.split(" "));

        model.addAttribute("selectedStartTimeslot", selectedStartTimeslot);
        model.addAttribute("selectedEndTimeslot", selectedEndTimeslot);

        if(dates.contains(8)) {
            for(int i = 1; i < 8; i++) {
                selectedDates.add(i);
            }
        }
        else {
            selectedDates = dates;
        }



        model.addAttribute("selectedDates", selectedDates);
        model.addAttribute("selectedFields", selectedFields);
        return "booking_summary";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("name", "hello");
        return "test";
    }
}
