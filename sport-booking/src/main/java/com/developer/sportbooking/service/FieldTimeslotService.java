package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Field_Timeslot;

import java.util.List;
import java.util.Optional;

public interface FieldTimeslotService {
    List<Field_Timeslot> findAllFieldTimeslot();
    List<Field_Timeslot> findFieldTimeslotByListId(List<Integer> fieldIds, Integer startTimeId, Integer endTimeId, List<Integer> dayIds);
}
