package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.entity.Timeslot;
import com.developer.sportbooking.repository.FieldRepo;
import com.developer.sportbooking.repository.FieldTimeslotRepo;
import com.developer.sportbooking.repository.TimeslotRepo;
import com.developer.sportbooking.service.FieldTimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.TemporalAdjusters;

@Service
public class FieldTimeslotImpl implements FieldTimeslotService {
    FieldTimeslotRepo fieldTimeslotRepo;
    FieldRepo fieldRepo;
    TimeslotRepo timeslotRepo;

    @Autowired
    public FieldTimeslotImpl(FieldTimeslotRepo fieldTimeslotRepo, FieldRepo fieldRepo, TimeslotRepo timeslotRepo) {
        this.fieldTimeslotRepo = fieldTimeslotRepo;
        this.fieldRepo = fieldRepo;
        this.timeslotRepo = timeslotRepo;
    }


    @Override
    public List<FieldTimeslot> findAllFieldTimeslot() {
        return fieldTimeslotRepo.findAll();
    }

    @Override
    public List<FieldTimeslot> findFieldTimeslotByListId(List<Long> fieldIds, Long startTimeId, Long endTimeId, List<Integer> dayIds) {
        List<FieldTimeslot> res = new ArrayList<>();
        for(Long fieldId : fieldIds) {
            for(Long timeId = startTimeId; timeId <= endTimeId; timeId++) {
                for(Integer dayId : dayIds) {
                    Field field = fieldRepo.findById(fieldId).isPresent() ? fieldRepo.findById(fieldId).get() : null;
                    Timeslot timeslot = timeslotRepo.findById(timeId).isPresent() ? timeslotRepo.findById(timeId).get() : null;

                    FieldTimeslot fieldTimeslot = fieldTimeslotRepo.findFieldTimeslotByFieldAndTimeslotAndDay(field, timeslot, com.developer.sportbooking.enumType.DayOfWeek.of(dayId));
                    res.add(fieldTimeslot);
                }
            }
        }

        return res;
    }
    @Override
    public Float calculateBookingFee(List<Long> fieldIds, Long startTimeId, Long endTimeId, List<Integer> dayIds, LocalDate bookingPeriod) {
        float totalFee = 0;
        List<FieldTimeslot> fieldTimeslots = this.findFieldTimeslotByListId(fieldIds, startTimeId, endTimeId, dayIds);

        LocalDate today = LocalDate.now();

        for (FieldTimeslot fieldTimeslot : fieldTimeslots) {
            int day = fieldTimeslot.getDay().getValue();
            DayOfWeek dow = DayOfWeek.of(day);
            LocalDate date = Year.of(bookingPeriod.getYear()).atMonth(bookingPeriod.getMonth()).atDay(1).with(TemporalAdjusters.firstInMonth(dow));
            Month mi = bookingPeriod.getMonth();
            while (mi == bookingPeriod.getMonth()) {
                if (date.isAfter(today) || date.isEqual(today)) {
                    totalFee += fieldTimeslot.getPrice();
                }
                date = date.with(TemporalAdjusters.next(dow));
                mi = date.getMonth();
            }
        }

        return totalFee;
    }

    @Override
    public List<FieldTimeslot> findFieldTimeslotByMonthAndYear(LocalDate monthAndYear) {
        List<FieldTimeslot> fieldTimeslots = new ArrayList<>();



        return fieldTimeslots;
    }

}
