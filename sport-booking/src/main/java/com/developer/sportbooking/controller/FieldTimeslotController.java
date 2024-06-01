package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.FieldTimeslotId;
import com.developer.sportbooking.entity.Field_Timeslot;
import com.developer.sportbooking.repository.FieldTimeslotRepo;
import com.developer.sportbooking.service.FieldTimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FieldTimeslotController {
    @Autowired
    FieldTimeslotService fieldTimeslotService;
    @Autowired
    FieldTimeslotRepo fieldTimeslotRepo;
    @GetMapping("/fieldTimeslot")
    public List<Field_Timeslot> findAllFieldTimeslot() {
        return fieldTimeslotService.findAllFieldTimeslot();
    }

    @GetMapping("/a")
    public Optional<Field_Timeslot> test() {
        return fieldTimeslotRepo.findById(new FieldTimeslotId(1, 1));
    }
}
