package com.app.flight.service;

import java.util.Map;

/**
 * @author zhenghan
 * @author LianJunhong
 */
public interface GetSeatMap {
    Map<Integer, Map<String, Boolean>> getSeatMap(String flightId);
}
