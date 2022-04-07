package com.app.flight.controller;

import com.app.flight.entity.Passenger;

/**
 * @author LianJunhong
 */
public class getfoodimpl implements getFoodtype{
    @Override
    public Passenger lookupPassenger(String ID) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(ID);
        passenger.setFirstName("Jun");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        return passenger;
    }
}
