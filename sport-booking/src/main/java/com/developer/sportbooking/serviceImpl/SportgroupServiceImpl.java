package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Sportgroup;
import com.developer.sportbooking.repository.SportgroupRepo;
import com.developer.sportbooking.service.SportgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SportgroupServiceImpl implements SportgroupService {
    @Autowired
    private SportgroupRepo sportgroupRepo;

    @Override
    public Sportgroup saveSportgroup(Sportgroup sportgroup) {
        return sportgroupRepo.save(sportgroup);
    }

    @Override
    public List<Sportgroup> findAllSportgroup() {
        return (List<Sportgroup>) sportgroupRepo.findAll();
    }

    @Override
    public Sportgroup updateSportgroupById(Sportgroup sportgroup, Integer id) {
        Sportgroup sportgroup1 = sportgroupRepo.findById(id).get();
        if (Objects.nonNull(sportgroup.getName())
                && !"".equalsIgnoreCase(
                sportgroup.getName())) {
            sportgroup1.setName(
                    sportgroup.getName());
        }
        return sportgroupRepo.save(sportgroup1);

    }

    @Override
    public void deleteSportgroupById(Integer id) {
        sportgroupRepo.deleteById(id);
    }
}
