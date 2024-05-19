package com.developer.sportbooking.controller;

import com.developer.sportbooking.entity.Sportgroup;
import com.developer.sportbooking.repository.SportgroupRepo;
import com.developer.sportbooking.service.SportgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SportgroupController {

    @Autowired
    private SportgroupService sportgroup_serv;

    // Save operation
    @PostMapping("/sportgroup")
    public Sportgroup saveSportgroup(@RequestBody Sportgroup sportgroup) {
        return sportgroup_serv.saveSportgroup(sportgroup);
    }

    // Read operation
    @GetMapping("/sportgroup")
    public List<Sportgroup> fetchAllSportgroup() {
        return sportgroup_serv.findAllSportgroup();
    }

    // Update operation
    @PutMapping("/sportgroup/{id}")
    public Sportgroup updateSportgroup(@PathVariable int id, @RequestBody Sportgroup sportgroup) {
        return sportgroup_serv.updateSportgroupById(sportgroup, id);
    }

    // Delete operation
    @DeleteMapping("/sportgroup/{id}")
    public String deleteSportgroup(@PathVariable int id) {
        sportgroup_serv.deleteSportgroupById(id);
        return "Deleted Sportgroup with id: " + id;
    }
}
