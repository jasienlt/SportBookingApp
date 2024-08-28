package com.developer.sportbooking.service;

import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.entity.Court;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CourtService {
    void saveCourt(CourtDto courtDto);

    ArrayList<Court> findAllCourt();

    Court findCourtById(Long id);

    Court updateCourtById(Court court, Long id);

    void deleteCourtById(Long id);

    Court findCourtByName(String name);

    Court findCourtByField(Long fieldId);

    List<Court> findCourtByAdmin(Long adminId);

    Court findCourtByNameAndPhone(String name, String phone);
}
