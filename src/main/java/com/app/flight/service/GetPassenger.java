package com.app.flight.service;

import com.app.flight.entity.Passenger;

/**
 * @author LianJunhong
 */
public interface GetPassenger {
    /**
     * Find passengers by ID
     *
     * @param passengerId id
     * @return Passenger
     */
    Passenger lookupPassengerById(String passengerId);

    /**
     * Find passengers by reservation number
     *
     * @param bookNumber id
     * @return Passenger
     */
    Passenger lookupPassengerByBookingNumber(String bookNumber);
}
