package com.app.flight.service;

import com.app.flight.entity.Passenger;

/**
 * @author LianJunhong
 */
public interface GetPassenger {
    /**
     * @param id id
     * @return Passenger
     */
    Passenger lookupPassengerById(String id);

    /**
     * @param bookNumber id
     * @return Passenger
     */
    Passenger lookupPassengerByBookingNumber(String bookNumber);
}
