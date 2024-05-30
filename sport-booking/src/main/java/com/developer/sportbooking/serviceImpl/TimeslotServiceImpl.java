package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.repository.TimeslotRepo;
import com.developer.sportbooking.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotServiceImpl implements TimeslotService {
    @Autowired
    private TimeslotRepo timeslotRepo;

    @Override
    public List<Timeslot> findAllTimeslot() {
        return timeslotRepo.findAll();
    }
}
