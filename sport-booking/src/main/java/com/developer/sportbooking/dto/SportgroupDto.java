package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportgroupDto {

    // If wanted to auto-increment, then implements Serializable for id
    private Long id;
    private String name;

    public SportgroupDto(String name) {
        this.name = name;
    }

}
