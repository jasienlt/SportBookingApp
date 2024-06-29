package com.developer.sportbooking.dto;

import lombok.Data;

@Data
public class ChargeRequest {
    public enum Currency {
        AUD, USD;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}
