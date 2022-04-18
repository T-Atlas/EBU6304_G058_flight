package com.app.flight.service;

import com.app.flight.entity.Flight;

/**
 * @author Jia Boran
 */

public interface GetFlight {

    /**
     *
     * @param id
     * @return
     */
    Flight lookupFlight(String id);
}
