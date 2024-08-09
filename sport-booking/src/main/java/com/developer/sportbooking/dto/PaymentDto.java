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
    private String paymentFile;
    private String paymentStatus;

    @Nullable
    private Long bookingId;

    public PaymentDto (Long bookingId, String paymentType) {
        this.bookingId = bookingId;
        this.paymentType = paymentType;
    }

    public PaymentDto(Long bookingId, String paymentType, String paymentStatus) {
        this.bookingId = bookingId;
        this.paymentType = paymentType;
        this.paymentFile = null;
        this.paymentStatus = paymentStatus;
    }

    public PaymentDto(Long bookingId, String paymentType, String paymentFile, String paymentStatus) {
        this.bookingId = bookingId;
        this.paymentType = paymentType;
        this.paymentFile = paymentFile;
        this.paymentStatus = paymentStatus;
    }
}
