package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtDto {
    private Long id;
    private String name;
    private String address;
    private String phone;

    @Nullable
    private Long sportgroupId;

    public CourtDto(String name, String address, String phone, @Nullable Long sportgroupId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sportgroupId = sportgroupId;
    }


}
