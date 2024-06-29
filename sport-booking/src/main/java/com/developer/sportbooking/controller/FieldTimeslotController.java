package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.CourtCustomerId;
import com.developer.sportbooking.entity.CourtCustomer;
import com.developer.sportbooking.entity.FieldTimeslotId;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.repository.CourtCustomerRepo;
import com.developer.sportbooking.repository.FieldTimeslotRepo;
import com.developer.sportbooking.service.FieldTimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FieldTimeslotController {
    FieldTimeslotService fieldTimeslotService;
    FieldTimeslotRepo fieldTimeslotRepo;
    CourtCustomerRepo courtCustomerRepo;

    @Autowired
    FieldTimeslotController(FieldTimeslotService fieldTimeslotService, FieldTimeslotRepo fieldTimeslotRepo, CourtCustomerRepo courtCustomerRepo) {
        this.fieldTimeslotService = fieldTimeslotService;
        this.fieldTimeslotRepo = fieldTimeslotRepo;
        this.courtCustomerRepo = courtCustomerRepo;
    }
    @GetMapping("/fieldTimeslot")
    public List<FieldTimeslot> findAllFieldTimeslot() {
        return fieldTimeslotService.findAllFieldTimeslot();
    }

    @GetMapping("/a")
    public Optional<FieldTimeslot> test() {
        return fieldTimeslotRepo.findById(1L);
    }
    @GetMapping("/b")
    public Optional<CourtCustomer> test1() {
        return courtCustomerRepo.findById(new CourtCustomerId(1L, 1L));
    }
}
