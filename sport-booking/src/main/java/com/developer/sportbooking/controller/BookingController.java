package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.*;
import com.developer.sportbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Controller
public class BookingController {
    public int bookingNo = 1;
    TimeslotService timeslotService;
    FieldService fieldService;
    FieldTimeslotService fieldTimeslotService;
    BookingService bookingService;
    CustomerService customerService;
    PaymentService paymentService;

    @Autowired
    public BookingController(TimeslotService timeslotService,
                             FieldService fieldService,
                             FieldTimeslotService fieldTimeslotService,
                             BookingService bookingService,
                             CustomerService customerService,
                             PaymentService paymentService) {
        this.timeslotService = timeslotService;
        this.fieldService = fieldService;
        this.fieldTimeslotService = fieldTimeslotService;
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.paymentService = paymentService;
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
    public Map<String, Float> bookingSummary(@RequestParam String fields,
                                               @RequestParam Long startBookingTime,
                                               @RequestParam Long endBookingTime,
                                               @RequestParam String dates) {

        float price = 0;
        Map<String, Float> response = new HashMap<>();

        List<Integer> selectedDates = new ArrayList<>();
        List<Long> selectedFields = new ArrayList<>();

        for(String s : dates.split(" ")) {
            selectedDates.add(Integer.parseInt(s));
        }

        for(String s : fields.split(" ")) {
            selectedFields.add(Long.parseLong(s));
        }

        price = fieldTimeslotService.calculateBookingFee(selectedFields, startBookingTime, endBookingTime, selectedDates);

        response.put("price", price);

        return response;
    }

    @GetMapping("/homepage")
    public String getHomepage() {
        return "homepage";
    }

    @PostMapping("/booking_summary")
    public String bookingSummary(@RequestParam Long selectedStartTimeslot,
                                 @RequestParam Long selectedEndTimeslot,
                                 @RequestParam(name = "date") List<Integer> dates,
                                 @RequestParam(name = "selectedFields") String selectedFieldsString,
                                 @RequestParam(name = "totalFee") String totalFee,
                                 Model model) {
        List<Integer> selectedDates = new ArrayList<>();
        List<Long> selectedFields = new ArrayList<>();

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
            selectedFields.add(Long.parseLong(s));
        }

        model.addAttribute("selectedDates", selectedDates);
        model.addAttribute("selectedFields", selectedFields);
        model.addAttribute("totalFee", totalFee);

        List<FieldTimeslot> fieldTimeslots = fieldTimeslotService.findFieldTimeslotByListId(selectedFields, selectedStartTimeslot, selectedEndTimeslot, selectedDates);

        Customer customer = customerService.getCustomerById(1L);
        Payment payment = paymentService.findPaymentById(1L);

        Booking booking = new Booking(1L, new Date(2024, 6, 19), (double) Float.parseFloat(totalFee), customer, payment, fieldTimeslots);
        bookingService.saveBooking(booking);


        return "booking_summary";
    }

}
