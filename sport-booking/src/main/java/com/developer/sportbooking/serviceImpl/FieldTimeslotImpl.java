package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.FieldTimeslotId;
import com.developer.sportbooking.entity.Field_Timeslot;
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
    public List<Field_Timeslot> findAllFieldTimeslot() {
        return fieldTimeslotRepo.findAll();
    }

    @Override
    public List<Field_Timeslot> findFieldTimeslotByListId(List<Long> fieldIds, Long startTimeId, Long endTimeId, List<Integer> dayIds) {
        List<Field_Timeslot> res = new ArrayList<>();

        for(Long fieldId : fieldIds) {
            for(Long timeId = startTimeId; timeId <= endTimeId; timeId++) {
                for(Integer dayId : dayIds) {
                    Optional<Field_Timeslot> fieldTimeslot = fieldTimeslotRepo.findById(new FieldTimeslotId(fieldId, timeId, dayId));
                    fieldTimeslot.ifPresent(res::add);
                }
            }
        }

        return res;
    }
}
