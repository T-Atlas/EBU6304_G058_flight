package com.app.flight.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.text.Format;
import java.time.LocalDateTime;

/**
 * @author SongBo
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBoardingGate() {
        return boardingGate;
    }

    public void setBoardingGate(String boardingGate) {
        this.boardingGate = boardingGate;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", boardingGate='" + boardingGate + '\'' +
                ", boardingTime=" + boardingTime +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
