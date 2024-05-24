package com.developer.sportbooking.service;

import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.entity.Court;

import java.util.List;

public interface CourtService {
    Court saveCourt(CourtDto courtDto);

    List<Court> findAllCourt();

    Court findCourtById(Integer id);

    Court updateCourtById(Court court, Integer id);

    void deleteCourtById(Integer id);
}
