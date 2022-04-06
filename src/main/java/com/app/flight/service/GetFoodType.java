package com.app.flight.service;

import com.app.flight.entity.Passenger;

/**
 * @author LianJunhong
 */
public interface GetFoodType {
    /**
     * apple
     * @param id id
     * @return Passenger
     */
    Passenger lookupPassenger(String id);
}
