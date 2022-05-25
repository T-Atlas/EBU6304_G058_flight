package com.app.flight.service;

import com.app.flight.entity.Reservation;
import com.app.flight.service.impl.GetReservationImpl;

import java.util.ArrayList;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.23
 * @see GetReservationImpl
 */
public interface GetReservation {

    /**
     * Find information about passenger's scheduled flight
     *
     * @param passengerId passenger ID
     * @return Information on all scheduled flights
     */
    ArrayList<Reservation> lookupReservations(String passengerId);
}
