package com.app.flight.service;

import java.util.ArrayList;

public interface Admin {

    /**
     * View passenger information of each flight by flight ID
     *
     * @param flightId Flight ID
     * @return Passenger information
     */
    ArrayList<String[]> searchCheckedInfoByFlightId(String flightId);

    /**
     * Get all flight ID
     *
     * @return All flight ID
     */
    ArrayList<String> getFlightId();

    /**
     * Query the password of the corresponding ID through the administrator ID
     *
     * @param id Admin ID
     * @return Admin password
     */
    String getPassword(String id);

    /**
     * Query the name of the corresponding ID through the administrator ID
     *
     * @param id Admin ID
     * @return Admin name
     */
    String getName(String id);
}
