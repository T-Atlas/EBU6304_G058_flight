package com.app.flight.entity;

import lombok.Data;

/**
 * @author LianJunhong
 * @author SongBo
 * @version 1.0
 * @date 2022.3.27
 */
@Data
public class Reservation {

    /**
     * The unique identification of particular reservation.
     */
    private String reservationId;

    /**
     * The passenger for the reserved flight.
     */
    private Passenger passenger;

    /**
     * The information for the reserved flight.
     */
    private Flight flight;

    /**
     * It is represented by three aircraft class enumeration constants.
     */
    private String seatLevel;

    /**
     * Determine whether meals are included in the scheduled flight.
     */
    private boolean mealsAvailable;

    /**
     * Number of passengers' hand baggage.
     */
    private int handBaggageNum;

    /**
     * Number of passengers' checked baggage.
     */
    private int checkedBaggageNum;

    public enum seatClass {
        /**
         * Three types of aircraft accommodation
         */
        ECONOMY_CLASS, BUSINESS_CLASS, FIRST_CLASS
    }

}
