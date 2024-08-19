package com.developer.sportbooking.serviceImpl;

import java.util.*;

import com.developer.sportbooking.entity.CanvasjsChartData;
import com.developer.sportbooking.entity.DataPointModel;
import com.developer.sportbooking.service.*;
import org.springframework.stereotype.Service;

@Service
public class CanvasjsChartServiceImpl implements CanvasjsChartService {

    @Override
    public List<List<DataPointModel>> getCanvasjsTwoAxis(HashMap<String, List<DataPointModel>> dataPoints) {

        CanvasjsChartData dataset = new CanvasjsChartData();
        List<List<DataPointModel>> finalList = dataset.getList();
        List<DataPointModel> dataRec = null;

        for (int j=0; j < dataPoints.size(); j++) {
            dataRec = (j == 0) ? dataset.getDataPoints1() :
                    (j == 1) ? dataset.getDataPoints2() :
                            (j == 2) ? dataset.getDataPoints3() :
                                    (j == 3) ? dataset.getDataPoints4() :
                                            (j == 4) ? dataset.getDataPoints5() :
                                                    (j == 5) ? dataset.getDataPoints6() : dataset.getDataPoints7();
            List<DataPointModel> dataSeries = dataPoints.get("set" + j);
            for (int i = 0; i < dataSeries.size(); j++) {
                dataRec.add(dataSeries.get(j));
            }
            finalList.add(dataRec);
        }
        return finalList;
    }
}
