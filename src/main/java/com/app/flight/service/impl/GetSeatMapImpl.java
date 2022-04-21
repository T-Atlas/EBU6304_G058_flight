package com.app.flight.service.impl;

import com.app.flight.service.GetSeatMap;
import com.app.flight.util.Csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.21
 */
public class GetSeatMapImpl implements GetSeatMap {
    @Override
    public Map<Integer, Map<String, Boolean>> getSeatMap(String flightId) {
        ArrayList<String[]> csvList = Csv.readCsv("src/main/resources/com/app/flight/data/csv/Flight.csv");
        String filePath = null;
        for (String[] csvData : csvList) {
            if (csvData[0].equals(flightId)) {
                String[] flightData = csvData.clone();
                String[] date = flightData[5].split(" ");
                filePath = "src/main/resources/com/app/flight/data/csv/flightSeat/" + flightData[0] + "_" + date[0] + ".csv";
                break;
            }
        }
        ArrayList<String[]> seatData = Csv.readCsv(filePath);
        Map<Integer, Map<String, Boolean>> seatMap = new HashMap<>(seatData.size());
        String[] rowString = new String[]{"A", "B", "C", "D", "E", "F"};
        int j = 1;
        for (String[] seatStatus : seatData) {
            Map<String, Boolean> rowMap = new HashMap<>(6);
            for (int i = 0; i < seatStatus.length; i++) {
                rowMap.put(rowString[i], Boolean.valueOf(seatStatus[i]));
            }
            seatMap.put(j, rowMap);
            j++;
        }
        return seatMap;
    }
}
