package com.app.flight.service;

import com.app.flight.service.impl.SeatMapImpl;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 * @see SeatMapImpl
 */
public interface SetSeatMap {
    /**
     * Modify the Boolean value of a seat
     *
     * @param flightId flightId
     * @param column   Modify the number of columns of seats
     * @param row      Modify the number of rows of seats
     * @param price    Modify the price of the selected seat
     */
    void updateSeatMap(String flightId, String column, int row, double price);
}
