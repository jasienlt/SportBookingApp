package com.developer.sportbooking.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class CanvasjsChartData {

    private Map<Object,Object> map;
    private List<List<DataPointModel>> list;
    private List<DataPointModel> dataPoints1;
    private List<DataPointModel> dataPoints2;
    private List<DataPointModel> dataPoints3;
    private List<DataPointModel> dataPoints4;
    private List<DataPointModel> dataPoints5;
    private List<DataPointModel> dataPoints6;
    private List<DataPointModel> dataPoints7;


    public CanvasjsChartData() {
        this.map = null;
        this.list = new ArrayList<List<DataPointModel>>();
        this.dataPoints1 = new ArrayList<>();
        this.dataPoints2 = new ArrayList<>();
        this.dataPoints3 = new ArrayList<>();
        this.dataPoints4 = new ArrayList<>();
        this.dataPoints5 = new ArrayList<>();
        this.dataPoints6 = new ArrayList<>();
        this.dataPoints7 = new ArrayList<>();
    }

}
