package com.app.flight.entity;

/**
 * @author LianJunhong
 */
public class Reservation {

    /**
     * The unique identification of particular reservation.
     */
    private String reservationId;

    /**
     * A flight number with six characters in total.
     * The first two characters are letters and the last four characters are numbers.
     */
    private String flightId;

    /**
     * It is represented by three aircraft class enumeration constants.
     */
    private String seatLevel;

    /**
     * The passenger for the reserved flight.
     */
    private Passenger passenger;

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

    public Reservation() {
    }


}
