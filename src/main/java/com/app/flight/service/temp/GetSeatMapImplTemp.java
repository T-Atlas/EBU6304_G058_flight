package com.app.flight.service.temp;

import com.app.flight.service.GetSeatMap;

import java.util.HashMap;
import java.util.Map;

public class GetSeatMapImplTemp implements GetSeatMap {
    @Override
    public Map<String, Boolean> getSeatMap(String flightId) {
        Map<String, Boolean> seatMap = new HashMap<>();
        seatMap.put("1a", true);
        seatMap.put("1b", false);
        seatMap.put("1c", true);
        seatMap.put("1d", true);
        seatMap.put("1e", true);
        seatMap.put("1f", true);
        return seatMap;
    }
}
