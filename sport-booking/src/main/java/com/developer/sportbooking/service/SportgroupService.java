package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Sportgroup;

import java.util.List;

public interface SportgroupService {
    Sportgroup saveSportgroup(Sportgroup sportgroup);

    List<Sportgroup> findAllSportgroup();

    Sportgroup findSportgroupById(Long id);

    Sportgroup updateSportgroupById(Sportgroup sportgroup, Long id);

    void deleteSportgroupById(Long id);
}
