package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.*;
import com.developer.sportbooking.enumType.BookingStatus;
import com.developer.sportbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Controller
public class BookingController {
    private final CourtService courtService;
    TimeslotService timeslotService;
    FieldService fieldService;
    FieldTimeslotService fieldTimeslotService;
    BookingService bookingService;
    CustomerService customerService;
    PaymentService paymentService;
    ReservedFieldTimeslotService reservedFieldTimeslotService;
    DateService dateService;

    @Autowired
    public BookingController(TimeslotService timeslotService,
                             FieldService fieldService,
                             FieldTimeslotService fieldTimeslotService,
                             BookingService bookingService,
                             CustomerService customerService,
                             PaymentService paymentService,
                             ReservedFieldTimeslotService reservedFieldTimeslotService,
                             DateService dateService, CourtService courtService) {
        this.timeslotService = timeslotService;
        this.fieldService = fieldService;
        this.fieldTimeslotService = fieldTimeslotService;
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.reservedFieldTimeslotService = reservedFieldTimeslotService;
        this.dateService = dateService;
        this.courtService = courtService;
    }

    @GetMapping("/booking")
    public String booking(Model model, @RequestParam(required = false) String courtId) {
        if (courtId == null) {
            return "redirect:/homepage";
        }

        List<Timeslot> timeslots = timeslotService.findAllTimeslotByCourtId(Long.valueOf(courtId));
        List<Field> fields = fieldService.findAllFieldByCourtId(Long.valueOf(courtId));
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
        model.addAttribute("courtId", courtId);

        return "booking";
    }

    @PostMapping("/booking")
    @ResponseBody
    public Map<String, Object> bookingSummary(@RequestBody Map<String, Object> requestBody) {
        String type = (String) requestBody.get("type");
        Map<String, Object> response = new HashMap<>();



        if("booking".equals(type)) {
            float price = 0;

            List<Integer> selectedDates = new ArrayList<>();
            List<Long> selectedFields = new ArrayList<>();
            Court court = null;



            for (String s : requestBody.get("dates").toString().split(" ")) {
                selectedDates.add(Integer.parseInt(s));
            }

            for (String s : requestBody.get("fields").toString().split(" ")) {
                selectedFields.add(Long.parseLong(s));
                court = courtService.findCourtByField(Long.parseLong(s));
            }
            if (court != null) {
                response.put("court", Long.toString(court.getId()));
            }


            price = fieldTimeslotService.calculateBookingFee(
                    selectedFields,
                    Long.parseLong(requestBody.get("startBookingTime").toString()),
                    Long.parseLong(requestBody.get("endBookingTime").toString()),
                    selectedDates,
                    dateService.convertStringToLocalDate((String) requestBody.get("bookingPeriod")));

            response.put("price", Float.toString(price));

            response.put("customerEmail", requestBody.get("customerEmail"));
            response.put("customerName", requestBody.get("customerName"));
        }
        else if ("date".equals(type)) {
            String date = (String) requestBody.get("date");
            LocalDate startDate = dateService.convertStringToLocalDate(date);
            LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

            HashMap<BookingStatus, List<ReservedFieldTimeslot>> bookingStatusListHashMap = reservedFieldTimeslotService.getAllReservedFieldBetweenTimePeriod(Date.valueOf(startDate), Date.valueOf(endDate));

            response.put("date", date);
            response.put("reservedFieldTimeslots", bookingStatusListHashMap.get(BookingStatus.COMPLETED));
            response.put("pendingFieldTimeslots", bookingStatusListHashMap.get(BookingStatus.PENDING));
        }

        return response;
    }
}
