package com.developer.sportbooking.dto;

import lombok.Data;

@Data
public class SportgroupDto {

    // If wanted to auto-increment, then implements Serializable for id
    private Integer id;
    private String name;

    public SportgroupDto() {}
    public SportgroupDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
