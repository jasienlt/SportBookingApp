package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.AwsConfig;
import com.developer.sportbooking.config.CustomCustomerDetails;
import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.PaymentStatus;
import com.developer.sportbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public String QRPayment(Model model, @ModelAttribute("courtId") String courtId,
                            @RequestParam Long selectedStartTimeslot,
                            @RequestParam Long selectedEndTimeslot,
                            @RequestParam(name = "date") List<Integer> dates,
                            @RequestParam(name = "selectedFields") String selectedFieldsString,
                            @RequestParam(name = "bookingPeriod") String bookingPeriodString,
                            @RequestParam(name = "totalFee") String totalFee,
                            @RequestParam(name = "customerEmail") String customerEmail,
                            @AuthenticationPrincipal CustomCustomerDetails customerDetails,
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
        model.addAttribute("selectedStartTimeslot", selectedStartTimeslot);
        model.addAttribute("selectedEndTimeslot", selectedEndTimeslot);
        model.addAttribute("dates", dates);
        model.addAttribute("selectedFields", selectedFieldsString);
        model.addAttribute("bookingPeriod", bookingPeriodString);
        model.addAttribute("custName", customerName);
        model.addAttribute("custEmail", customerEmail);
        model.addAttribute("customerDetails", customerDetails);

        return "paymentReceipt";
    }

    @PostMapping("/finishPayment")
    public String QRPayment(Model model,
                            @ModelAttribute("custName") String custName,
                            @ModelAttribute("custEmail") String custEmail,
                            @ModelAttribute("selectedStartTimeslot") Long selectedStartTimeslot,
                            @ModelAttribute("selectedEndTimeslot") Long selectedEndTimeslot,
                            @ModelAttribute("date") List<Integer> dates,
                            @ModelAttribute("selectedFields") String selectedFieldsString,
                            @ModelAttribute("totalFee") String totalFee,
                            @ModelAttribute("bookingPeriod") String bookingPeriodString,
                            @RequestParam(name = "receiptImg") MultipartFile multipart,
                            @AuthenticationPrincipal CustomCustomerDetails customerDetails) {

        String fileName = custName + Date.valueOf(LocalDate.now()).toString();

        String message = "";

        try {
            AwsConfig.uploadFile(fileName, multipart.getInputStream(),"payment_screenshots");
            message = "Your file has been uploaded successfully!";
        }
        catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }

        model.addAttribute("message", message);
        bookingService.saveBookingSummary(selectedStartTimeslot, selectedEndTimeslot, dates, selectedFieldsString, totalFee, bookingPeriodString, fileName, "Bank Transfer",
                customerDetails == null ? new Customer(custEmail) : customerDetails.getCustomer());

        Payment payment = new Payment(LocalDateTime.now(),"Bank Transfer",fileName,PaymentStatus.PENDING,bookingService.getBookingBySessionId(fileName).getId());
        paymentService.savePayment(payment);

        return "success";
    }

}
