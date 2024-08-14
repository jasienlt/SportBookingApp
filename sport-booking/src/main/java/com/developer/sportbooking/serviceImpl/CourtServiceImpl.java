package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.entity.Sportgroup;
import com.developer.sportbooking.repository.CourtRepo;
import com.developer.sportbooking.repository.SportgroupRepo;
import com.developer.sportbooking.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourtServiceImpl implements CourtService {
    @Autowired
    private CourtRepo courtRepo;
    @Autowired
    private SportgroupRepo sportgroupRepo;

//    @Override
//    public Sportgroup saveSportgroup(Sportgroup sportgroup) {
//        return sportgroupRepo.save(sportgroup);
//    }

    @Override
    public Court saveCourt(CourtDto courtDto) {
        Court court = null;

        // If court belongs to a sportgroup
        if (courtDto.getSportgroupId() != 0) {
            Sportgroup sportgroup = null;
            boolean sportgroupExist = sportgroupRepo.existsById(courtDto.getSportgroupId());
            if (sportgroupExist) {
                sportgroup = sportgroupRepo.getById(courtDto.getSportgroupId());
                court = new Court(courtDto.getId(),courtDto.getName(), court.getAddress(), courtDto.getPhone(),sportgroup);
            } else {
                court = new Court(courtDto.getId(),courtDto.getName(), court.getAddress(), courtDto.getPhone(),null);
            }
        }
        return courtRepo.save(court);
    }


    @Override
    public List<Court> findAllCourt() {
        return (List<Court>) courtRepo.findAll();
    }



    @Override
    public Court findCourtById(Long courtId) {
        if (courtRepo.findById(courtId).isPresent()) {
            return courtRepo.findById(courtId).get();
        }
        return null;
    }

    @Override
    public Court updateCourtById(Court court, Long id) {
        Court court1 = courtRepo.findById(id).get();
        if (Objects.nonNull(court.getName())
                && !"".equalsIgnoreCase(
                court.getName())) {
            court1.setName(
                    court.getName());
        }
        return courtRepo.save(court1);
    }

    @Override
    public void deleteCourtById(Long id) {
        courtRepo.deleteById(id);
    }

    @Override
    public Court findCourtByName(String name) {
        return courtRepo.findByName(name);
    }

    @Override
    public Court findCourtByField(Long fieldId) {
        return courtRepo.findByField(fieldId);
    }
}
