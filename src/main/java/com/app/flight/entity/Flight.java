package com.app.flight.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author SongBo
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
 */
@Data
public class Flight {

    /**
     * A flight number with six characters in total.
     * The first two characters are letters and the last four characters are numbers.
     */
    @JSONField(ordinal = 1)
    private String flightId;

    /**
     * Flight departure location.
     */
    @JSONField(ordinal = 2)
    private String departure;

    /**
     * Flight destination.
     */
    @JSONField(ordinal = 3)
    private String destination;

    /**
     * Boarding gate of flight.
     */
    @JSONField(ordinal = 4)
    private String boardingGate;

    /**
     * Boarding time of flight.
     */
    @JSONField(format = "yyyy-MM-dd HH:mm", ordinal = 5)
    private LocalDateTime boardingTime;

    /**
     * Departure time of flight.
     */
    @JSONField(format = "yyyy-MM-dd HH:mm", ordinal = 6)
    private LocalDateTime departureTime;

    /**
     * Arrival time of flight.
     */
    @JSONField(format = "yyyy-MM-dd HH:mm", ordinal = 7)
    private LocalDateTime arrivalTime;

}
