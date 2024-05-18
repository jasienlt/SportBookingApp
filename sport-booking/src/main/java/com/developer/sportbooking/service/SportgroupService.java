package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Sportgroup;

import java.util.List;

public interface SportgroupService {
    Sportgroup saveSportgroup(Sportgroup sportgroup);

    List<Sportgroup> findAllSportgroup();

    Sportgroup updateSportgroupById(Sportgroup sportgroup, Integer id);

    void deleteSportgroupById(Integer id);
}
