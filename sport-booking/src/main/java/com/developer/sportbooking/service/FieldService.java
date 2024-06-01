package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.entity.Timeslot;

import java.util.List;

public interface FieldService {
    List<Field> findAllField();
}
