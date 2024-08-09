package com.developer.sportbooking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourtDto {
    private Long id;
    private String name;
    private String address;
    private String phone;

    @Nullable
    private Long sportgroupId;

    @Nullable
    private Long managedBy;

    public CourtDto(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sportgroupId = null;
        this.managedBy = null;
    }


    public CourtDto(String name, String address, String phone, Long sportgroupId, Long managedBy) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sportgroupId = sportgroupId;
        this.managedBy = managedBy;
    }


}
