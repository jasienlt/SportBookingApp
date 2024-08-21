package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.dto.CourtDto;
import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.entity.Sportgroup;
import com.developer.sportbooking.repository.CourtRepo;
import com.developer.sportbooking.repository.CustomerRepo;
import com.developer.sportbooking.repository.SportgroupRepo;
import com.developer.sportbooking.service.CourtService;
import com.developer.sportbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourtServiceImpl implements CourtService {
    @Autowired
    private CourtRepo courtRepo;
    @Autowired
    private SportgroupRepo sportgroupRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerService customerService;

//    @Override
//    public Sportgroup saveSportgroup(Sportgroup sportgroup) {
//        return sportgroupRepo.save(sportgroup);
//    }

    @Override
    public void saveCourt(CourtDto courtDto) {
        Court court = null;

        // If court belongs to a sportgroup
        if (courtDto.getSportgroupId().getId() != 0) {
            Sportgroup sportgroup = null;
            boolean sportgroupExist = sportgroupRepo.existsById(courtDto.getSportgroupId().getId());
            if (sportgroupExist) {
                sportgroup = sportgroupRepo.getById(courtDto.getSportgroupId().getId());
                court = new Court(courtDto.getName(), courtDto.getAddress(), courtDto.getPhone(),sportgroup, customerService.getCustomerById(courtDto.getManagedBy().getId()));
            } else {
                court = new Court(courtDto.getName(), courtDto.getAddress(), courtDto.getPhone(),null,customerService.getCustomerById(courtDto.getManagedBy().getId()));
            }
        }
        courtRepo.save(court);
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

    @Override
    public List<Court> findCourtByAdmin(Long adminId) {
        return courtRepo.findByManagedBy(adminId);
    }

    @Override
    public Court findCourtByNameAndPhone(String name, String phone) {
        return courtRepo.findByNameAndPhone(name,phone);
    }
}
