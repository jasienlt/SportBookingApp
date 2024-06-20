package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.FieldTimeslotId;
import com.developer.sportbooking.entity.FieldTimeslot;
import com.developer.sportbooking.repository.FieldTimeslotRepo;
import com.developer.sportbooking.service.FieldTimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FieldTimeslotImpl implements FieldTimeslotService {
    FieldTimeslotRepo fieldTimeslotRepo;
    @Autowired
    public FieldTimeslotImpl(FieldTimeslotRepo fieldTimeslotRepo) {
        this.fieldTimeslotRepo = fieldTimeslotRepo;
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
                    Optional<FieldTimeslot> fieldTimeslot = fieldTimeslotRepo.findById(new FieldTimeslotId(fieldId, timeId, dayId));
                    fieldTimeslot.ifPresent(res::add);
                }
            }
        }

        return res;
    }
    @Override
    public Float calculateBookingFee(List<Long> fieldIds, Long startTimeId, Long endTimeId, List<Integer> dayIds) {
        float totalFee = 0;

        List<FieldTimeslot> fieldTimeslots = this.findFieldTimeslotByListId(fieldIds, startTimeId, endTimeId, dayIds);

        for(FieldTimeslot fieldTimeslot : fieldTimeslots) {
            totalFee += fieldTimeslot.getPrice();
        }

        return totalFee;
    }
}
