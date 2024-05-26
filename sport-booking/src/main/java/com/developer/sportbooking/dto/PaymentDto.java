package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Integer id;
    private String paymentType;

    @Nullable
    private String bookingId;

    public PaymentDto(Integer id, String paymentType) {
        this.id = id;
        this.paymentType = paymentType;
    }
}
