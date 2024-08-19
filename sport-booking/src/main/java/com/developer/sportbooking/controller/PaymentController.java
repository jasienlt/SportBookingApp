package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.AwsConfig;
import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.PaymentStatus;
import com.developer.sportbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class PaymentController {

    TimeslotService timeslotService;
    FieldService fieldService;
    FieldTimeslotService fieldTimeslotService;
    BookingService bookingService;
    CustomerService customerService;
    PaymentService paymentService;
    ReservedFieldTimeslotService reservedFieldTimeslotService;
    DateService dateService;

    @Autowired
    public PaymentController(TimeslotService timeslotService,
                             FieldService fieldService,
                             FieldTimeslotService fieldTimeslotService,
                             BookingService bookingService,
                             CustomerService customerService,
                             PaymentService paymentService,
                             ReservedFieldTimeslotService reservedFieldTimeslotService,
                             DateService dateService) {
        this.timeslotService = timeslotService;
        this.fieldService = fieldService;
        this.fieldTimeslotService = fieldTimeslotService;
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.reservedFieldTimeslotService = reservedFieldTimeslotService;
        this.dateService = dateService;
    }

    @PostMapping("/finishBooking")
    @ResponseBody
    public String QRPayment(Model model, @ModelAttribute("courtId") String courtId,
                            @RequestParam Long selectedStartTimeslot,
                            @RequestParam Long selectedEndTimeslot,
                            @RequestParam(name = "date") List<Integer> dates,
                            @RequestParam(name = "selectedFields") String selectedFieldsString,
                            @RequestParam(name = "totalFee") String totalFee,
                            @RequestParam(name = "bookingPeriod") String bookingPeriodString,
                            @RequestParam(name = "customerEmail") String customerEmail,
                            @RequestParam(name = "customerName") String customerName) {

        String message = "";
        String file = "";
        try {
            file = AwsConfig.getImage(courtId, "payment_screenshots");
            message = "Your file has been retrieved successfully!";
        }
        catch (Exception ex) {
            message = "Error retrieving file: " + ex.getMessage();
        }

        model.addAttribute("totalFee", totalFee);
        model.addAttribute("message", message);
        model.addAttribute("imgAsBase64", file);
        model.addAttribute("customerName", customerName);

        return "paymentReceipt";


    }

    @PostMapping("/finishPayment")
    public String QRPayment(Model model, ModelAttribute modelAttribute,
                            @RequestParam Long selectedStartTimeslot,
                            @RequestParam Long selectedEndTimeslot,
                            @RequestParam(name = "date") List<Integer> dates,
                            @RequestParam(name = "selectedFields") String selectedFieldsString,
                            @RequestParam(name = "totalFee") String totalFee,
                            @RequestParam(name = "bookingPeriod") String bookingPeriodString,
                            @RequestParam(name = "receiptImg") MultipartFile multipart) {

        String fileName = multipart.getOriginalFilename();

        String message = "";

        try {
            AwsConfig.uploadFile(fileName, multipart.getInputStream(),"payment_screenshots");
            message = "Your file has been uploaded successfully!";
        }
        catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }

        model.addAttribute("message", message);
        bookingService.saveBookingSummary(selectedStartTimeslot, selectedEndTimeslot, dates, selectedFieldsString, totalFee, bookingPeriodString, fileName, "Bank Transfer");

        return "success";
    }

}
