package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Float price;
    private Integer amount;

    @Nullable
    private Long courtId;

    public ProductDto(String name, Float price, Integer amount, @Nullable Long courtId) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.courtId = courtId;
    }
}
