package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.entity.Field_Timeslot;
import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.service.FieldService;
import com.developer.sportbooking.service.FieldTimeslotService;
import com.developer.sportbooking.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.*;

@Controller
public class BookingController {
    public int orderNo = 1;
    TimeslotService timeslotService;
    FieldService fieldService;
    FieldTimeslotService fieldTimeslotService;

    @Autowired
    public BookingController(TimeslotService timeslotService, FieldService fieldService, FieldTimeslotService fieldTimeslotService) {
        this.timeslotService = timeslotService;
        this.fieldService = fieldService;
        this.fieldTimeslotService = fieldTimeslotService;
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        List<Timeslot> timeslots = timeslotService.findAllTimeslot();
        List<Field> fields = fieldService.findAllField();
        List<String> timeslotStartTime = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        String month = currentMonth > 9 ? Integer.toString(currentMonth) : "0" + Integer.toString(currentMonth);
        String startMonthAndYear = Integer.toString(currentYear) + "-" + month ;

        for(Timeslot timeslot : timeslots) {
            timeslotStartTime.add(timeslot.getStartTime().toString().substring(0, timeslot.getStartTime().toString().length() - 3));
        }

        model.addAttribute("timeslots", timeslots);
        model.addAttribute("fields", fields);
        model.addAttribute("timeslotStartTime", timeslotStartTime);
        model.addAttribute("currentDate", startMonthAndYear);

        return "booking";
    }

    @PostMapping("/booking")
    @ResponseBody
    public Map<String, Integer> bookingSummary(@RequestParam String fields,
                                               @RequestParam Integer startBookingTime,
                                               @RequestParam Integer endBookingTime,
                                               @RequestParam String dates) {

        int price = 0;
        Map<String, Integer> response = new HashMap<>();

        List<Integer> selectedDates = new ArrayList<>();
        List<Integer> selectedFields = new ArrayList<>();

        for(String s : dates.split(" ")) {
            selectedDates.add(Integer.parseInt(s));
        }

        for(String s : fields.split(" ")) {
            selectedFields.add(Integer.parseInt(s));
        }

        List<Field_Timeslot> fieldTimeslots = fieldTimeslotService.findFieldTimeslotByListId(selectedFields, startBookingTime, endBookingTime, selectedDates);

        for(Field_Timeslot fieldTimeslot : fieldTimeslots) {
            price += fieldTimeslot.getPrice();
        }

        response.put("price", price);

        return response;
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
        List<Integer> selectedFields = new ArrayList<>();

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

        for(String s : selectedFieldsString.split(" ")) {
            selectedFields.add(Integer.parseInt(s));
        }

        model.addAttribute("selectedDates", selectedDates);
        model.addAttribute("selectedFields", selectedFields);

        orderNo += 1;

        return "booking_summary";
    }
}
