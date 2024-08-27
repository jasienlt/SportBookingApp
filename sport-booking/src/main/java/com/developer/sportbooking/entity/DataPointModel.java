package com.developer.sportbooking.entity;

import lombok.Data;

@Data
public class DataPointModel <X,Y>{
    X x;
    Y y;

    public DataPointModel(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}
