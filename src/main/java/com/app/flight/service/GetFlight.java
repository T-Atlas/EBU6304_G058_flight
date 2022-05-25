package com.app.flight.service;

import com.app.flight.entity.Flight;
import com.app.flight.service.impl.GetFlightImpl;

/**
 * @author Jia Boran
 * @version 3.0
 * @see GetFlightImpl
 */

public interface GetFlight {

    /**
     * Find flights by flight name.
     *
     * @param flightId flight ID
     * @return flight information
     */
    Flight lookupFlight(String flightId);
}
