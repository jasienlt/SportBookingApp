package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.FieldTimeslot;

import java.util.List;

public interface FieldTimeslotService {
    List<FieldTimeslot> findAllFieldTimeslot();
    List<FieldTimeslot> findFieldTimeslotByListId(List<Long> fieldIds, Long startTimeId, Long endTimeId, List<Integer> dayIds);
    Float calculateBookingFee(List<Long> fieldIds, Long startTimeId, Long endTimeId, List<Integer> dayIds);
}
