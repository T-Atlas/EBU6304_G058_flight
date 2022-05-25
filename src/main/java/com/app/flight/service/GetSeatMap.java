package com.app.flight.service;

import com.app.flight.service.impl.SeatMapImpl;

import java.util.Map;

/**
 * @author zhenghan
 * @see SeatMapImpl
 */
public interface GetSeatMap {
    /**
     * Return the seat map data of the flight
     *
     * @param flightId Flight No.
     * @return SeatUtil information map
     */
    Map<Integer, Map<String, Boolean>> lookupSeatMap(String flightId);
}
