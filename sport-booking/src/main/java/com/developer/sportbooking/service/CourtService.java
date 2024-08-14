package com.developer.sportbooking.service;

import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.entity.Court;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourtService {
    Court saveCourt(CourtDto courtDto);

    List<Court> findAllCourt();

    Court findCourtById(Long id);

    Court updateCourtById(Court court, Long id);

    void deleteCourtById(Long id);

    Court findCourtByName(String name);
}
