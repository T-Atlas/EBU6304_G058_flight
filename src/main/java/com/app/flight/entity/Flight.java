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
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime boardingTime;

    /**
     * Departure time of flight.
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;

    /**
     * Arrival time of flight.
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

}
