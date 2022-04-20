package com.app.flight.service.temp;

import com.app.flight.service.GetSeatMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenghan
 */
public class GetSeatMapImplTemp implements GetSeatMap {
    @Override
    public Map<Integer, Map<String, Boolean>> getSeatMap(String flightId) {
        Map<String, Boolean> rowMap = new HashMap<>(50);
        rowMap.put("A", true);
        rowMap.put("B", false);
        rowMap.put("C", true);
        rowMap.put("D", true);
        rowMap.put("E", true);
        rowMap.put("F", true);

        Map<Integer, Map<String, Boolean>> seatMap = new HashMap<>(50);
        seatMap.put(12, rowMap);
        seatMap.put(11, rowMap);
        seatMap.put(10, rowMap);
        seatMap.put(9, rowMap);
        seatMap.put(8, rowMap);
        seatMap.put(7, rowMap);
        seatMap.put(1, rowMap);
        seatMap.put(2, rowMap);
        seatMap.put(3, rowMap);
        seatMap.put(4, rowMap);
        seatMap.put(5, rowMap);
        seatMap.put(6, rowMap);

        return seatMap;
    }
}
