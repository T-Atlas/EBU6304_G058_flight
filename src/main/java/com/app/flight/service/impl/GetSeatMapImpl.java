package com.app.flight.service.impl;

import com.app.flight.service.GetSeatMap;
import com.app.flight.util.Csv;
import com.app.flight.util.Seat;

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
    public Map<Integer, Map<String, Boolean>> lookupSeatMap(String flightId) {
        ArrayList<String[]> seatData = Csv.readCsv(Seat.generateSeatFilePath(flightId));
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
