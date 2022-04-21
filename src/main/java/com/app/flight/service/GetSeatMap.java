package com.app.flight.service;

import java.util.Map;

/**
 * @author zhenghan
 */
public interface GetSeatMap {
    /**
     * Return the seat map data of the flight
     *
     * @param flightId Flight No.
     * @return Seat information map
     */
    Map<Integer, Map<String, Boolean>> getSeatMap(String flightId);
}
