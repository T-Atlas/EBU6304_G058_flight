package com.app.flight.service;

import java.util.Map;

/**
 * @author zhenghan
 */
public interface GetSeatMap {
    Map<Integer, Map<String, Boolean>> getSeatMap(String flightId);
}
