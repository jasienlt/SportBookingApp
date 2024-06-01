package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Field_Timeslot;
import com.developer.sportbooking.repository.FieldTimeslotRepo;
import com.developer.sportbooking.service.FieldTimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldTimeslotImpl implements FieldTimeslotService {
    @Autowired
    FieldTimeslotRepo fieldTimeslotRepo;
    @Override
    public List<Field_Timeslot> findAllFieldTimeslot() {
        return fieldTimeslotRepo.findAll();
    }
}
