package com.developer.sportbooking.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.developer.sportbooking.chartDto.BookingCount;
import com.developer.sportbooking.dto.CustomerDto;
import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.entity.DataPointModel;
import com.developer.sportbooking.enumType.BookingStatus;
import com.developer.sportbooking.repository.BookingRepo;
import com.developer.sportbooking.service.CanvasjsChartService;
import com.developer.sportbooking.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/dashboardAdmin")
public class CanvasjsChartController {

    @Autowired
    private CanvasjsChartService canvasjsChartService;
    @Autowired
    private CourtService courtService;
    @Autowired
    private BookingRepo bookingRepo;

    @RequestMapping(method = RequestMethod.GET)
    public String springMVC(ModelMap modelMap, CustomerDto customerDto) {
        List<Court> managedCourt = courtService.findCourtByAdmin(customerDto.getId());
        try {
            List<List<DataPointModel>> statusChart = bookingStatusStackedBar(courtService.findCourtById(1L));
            modelMap.addAttribute("dataPointsList", statusChart);

            List<Booking> statusTable = bookingStatusTable(courtService.findCourtByField(1L));
            modelMap.addAttribute("statusTable", statusTable);

        } catch (NullPointerException e) {
            modelMap.addAttribute("message", "No booking found");
        }
        return "stackedBar";
    }

    @RequestMapping(value = "/updateChartData", method = RequestMethod.GET)
    @ResponseBody
    public List<List<DataPointModel>> updateChartData(CustomerDto customerDto) {
        List<Court> managedCourt = courtService.findCourtByAdmin(customerDto.getId());
        try {
            return bookingStatusStackedBar(courtService.findCourtById(1L));
        } catch (NullPointerException e) {
            return new ArrayList<>(); // Return empty list if there's an error
        }
    }

    private List<List<DataPointModel>> bookingStatusStackedBar(Court court) {

        // To be added with Court ID for filtering in future
        List<BookingCount> dataOutput = bookingRepo.findByCourtAndStatus(1L, Date.valueOf(LocalDate.now().minusDays(7)));
        System.out.println(dataOutput.toString());
        HashMap<String, List<DataPointModel>> datasets = new HashMap<>();

        List<DataPointModel> set1 = new ArrayList<>();
        List<DataPointModel> set2 = new ArrayList<>();
        List<DataPointModel> set3 = new ArrayList<>();
        for (BookingCount bookingCount : dataOutput) {
            if (bookingCount.getBookingStatus() == BookingStatus.CANCELED) {
                DataPointModel dataforSet1 = new DataPointModel(bookingCount.getBookingDate(),bookingCount.getTotal());
                set1.add(dataforSet1);
            } else if (bookingCount.getBookingStatus() == BookingStatus.PENDING) {
                DataPointModel dataforSet2 = new DataPointModel(bookingCount.getBookingDate(),bookingCount.getTotal());
                set2.add(dataforSet2);
            } else {
                DataPointModel dataforSet3 = new DataPointModel(bookingCount.getBookingDate(),bookingCount.getTotal());
                set3.add(dataforSet3);
            }
        }
        datasets.put("set1", set1);
        datasets.put("set2", set2);
        datasets.put("set3", set3);

        return canvasjsChartService.getCanvasjsTwoAxis(datasets);
    }

    private List<Booking> bookingStatusTable(Court court) {
        return bookingRepo.findBookingsByCourt(court.getId());
    }

}