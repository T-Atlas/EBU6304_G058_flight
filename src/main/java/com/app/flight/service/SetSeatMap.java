package com.app.flight.service;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public interface SetSeatMap {
    /**
     * Modify the Boolean value of a seat
     *
     * @param flightId flightId
     * @param row      Modify the number of rows of seats
     * @param column   Modify the number of columns of seats
     */
    void updateSeatMap(String flightId, String row, int column);
}
