package com.developer.sportbooking.controller;

import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CourtController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CourtService court_serv;

    // Save operation
    @PostMapping("/court")
    public Court saveSportgroup(@RequestBody CourtDto courtDto) {
        return court_serv.saveCourt(courtDto);
    }

    // Read operation
    @GetMapping("/court")
    public List<Court> fetchAllSportgroup() {
        return court_serv.findAllCourt();
    }

    @GetMapping("/court")
    public Court fetchSportgroupById(@RequestParam Integer id) {
        return court_serv.findCourtById(id);
    }

    // Update operation
    @PutMapping("/court/{id}")
    public Court updateSportgroup(@PathVariable Integer id, @RequestBody Court court) {
        return court_serv.updateCourtById(court, id);
    }

    // Delete operation
    @DeleteMapping("/court/{id}")
    public String deleteSportgroup(@PathVariable Integer id) {
        court_serv.deleteCourtById(id);
        return "Deleted Sportgroup with id: " + id;
    }
}

