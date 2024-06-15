package com.developer.sportbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long id;
    private String paymentType;

    @Nullable
    private Long bookingId;

    public PaymentDto(String paymentType, @Nullable Long bookingId) {
        this.paymentType = paymentType;
        this.bookingId = bookingId;
    }
}
