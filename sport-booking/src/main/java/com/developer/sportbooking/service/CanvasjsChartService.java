package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.CanvasjsChartData;
import com.developer.sportbooking.entity.DataPointModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface CanvasjsChartService {

    List<List<DataPointModel>> getCanvasjsTwoAxis(HashMap<String, List<DataPointModel>> dataPoints);


}
