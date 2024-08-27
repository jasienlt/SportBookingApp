package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Field;
import com.developer.sportbooking.repository.FieldRepo;
import com.developer.sportbooking.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    FieldRepo fieldRepo;
    @Override
    public List<Field> findAllField() {
        return fieldRepo.findAll();
    }

    @Override
    public List<Field> findAllFieldByCourtId(Long courtId) {
        return fieldRepo.findAllByCourt_Id(courtId);
    }
}
