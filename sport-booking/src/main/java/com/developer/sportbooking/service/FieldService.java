package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.entity.Timeslot;

import java.util.List;

public interface FieldService {
    Field saveField(Field field);

    List<Field> findAllField();

    Field findFieldById(Long id);

    Field findFieldByCourtId(Long id);

    Field findFieldByTimeslotId(Long id);

    Field updateFieldById(Field field, Long id);

    void deleteFieldById(Long id);
}
