package com.app.flight.service.impl;

import com.app.flight.service.SetSeatMap;
import com.app.flight.util.Seat;

import java.util.Map;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public class SetSeatMapImpl implements SetSeatMap {
    @Override
    public void updateSeatMap(String flightId, String row, int column) {
        GetSeatMapImpl getSeatMap = new GetSeatMapImpl();
        Map<Integer, Map<String, Boolean>> seatMap = getSeatMap.lookupSeatMap(flightId);
        Map<String, Boolean> stringBooleanMap = seatMap.get(column);
        Boolean status = stringBooleanMap.get(row);
        status = !status;
        stringBooleanMap.replace(row, status);
        seatMap.replace(column, stringBooleanMap);
        String[] rowString = new String[]{"A", "B", "C", "D", "E", "F"};
        Seat.writeSeatMap(Seat.generateSeatFilePath(flightId), seatMap, rowString);
    }
}
