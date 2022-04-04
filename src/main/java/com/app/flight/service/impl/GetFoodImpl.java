package com.app.flight.service.impl;

import com.app.flight.entity.Passenger;
import com.app.flight.service.GetFoodType;

/**
 * @author LianJunhong
 */
public class GetFoodImpl implements GetFoodType {
    @Override
    public Passenger lookupPassenger(String id) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);
        passenger.setFirstName("Jun");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        return passenger;
    }
}
