package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Timeslot;

import java.util.List;

public interface TimeslotService {
    Timeslot saveTimeslot(Timeslot timeslot);

    List<Timeslot> findAllTimeslot();

    Timeslot findTimeslotById(Integer id);

    Timeslot findTimeslotByCourtId(Integer id);

    Timeslot findTimeslotByFieldId(Integer id);

    Timeslot updateTimeslotById(Timeslot timeslot, Integer id);

    void deleteTimeslotById(Integer id);
}
