package com.app.flight.entity;

import com.alibaba.fastjson2.annotation.JSONField;
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
    @JSONField(ordinal = 1)
    private String reservationId;

    /**
     * The passenger for the reserved flight.
     */
    @JSONField(ordinal = 2)
    private Passenger passenger;

    /**
     * The information for the reserved flight.
     */
    @JSONField(ordinal = 3)
    private Flight flight;

    /**
     * It is represented by three aircraft class enumeration constants.
     */
    @JSONField(ordinal = 4)
    private seatClass seatLevel;

    /**
     * Determine whether meals are included in the scheduled flight.
     */
    @JSONField(ordinal = 5)
    private boolean mealsAvailable;

    /**
     * Number of passengers' hand baggage.
     */
    @JSONField(ordinal = 6)
    private int handBaggageNum;

    /**
     * Number of passengers' checked baggage.
     */
    @JSONField(ordinal = 7)
    private int checkedBaggageNum;

    public enum seatClass {
        /**
         * Three types of aircraft accommodation
         */
        ECONOMY_CLASS, BUSINESS_CLASS, FIRST_CLASS
    }

}
