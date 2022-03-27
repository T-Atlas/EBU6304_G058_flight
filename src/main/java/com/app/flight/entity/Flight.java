package com.app.flight.entity;

import java.time.LocalDateTime;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Flight {

    /**
     * A flight number with six characters in total.
     * The first two characters are letters and the last four characters are numbers.
     */
    private String flightId;

    /**
     * Flight departure location.
     */
    private String departure;

    /**
     * Flight destination.
     */
    private String destination;

    /**
     * Boarding gate of flight.
     */
    private String boardingGate;

    /**
     * Boarding time of flight.
     */
    private LocalDateTime boardingTime;

    /**
     * Departure time of flight.
     */
    private LocalDateTime departureTime;

    /**
     * Arrival time of flight.
     */
    private LocalDateTime arrivalTime;


    public Flight() {
    }
}
