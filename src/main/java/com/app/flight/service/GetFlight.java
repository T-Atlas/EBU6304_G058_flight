package com.app.flight.service;

import com.app.flight.entity.Flight;

/**
 * @author Jia Boran
 */

public interface GetFlight {

    /**
     * Find flights by flight name
     *
     * @param flightId flight ID
     * @return flight information
     */
    Flight lookupFlight(String flightId);
}
