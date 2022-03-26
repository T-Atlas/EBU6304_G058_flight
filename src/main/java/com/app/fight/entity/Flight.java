package com.app.fight.entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Flight {
    private String flightId;
    private String departureStation;
    private String destination;
    private String seatLevel;
    private boolean baggage;
    private boolean food;
    private LocalTime time;
    private LocalDate date;

    public enum seatClass {
        //Three types of aircraft accommodation
        ECONOMY_CLASS, BUSINESS_CLASS, First_CLASS
    }

    public Flight() {
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSeatLevel() {
        return seatLevel;
    }

    public void setSeatLevel(String seatLevel) {
        this.seatLevel = seatLevel;
    }

    public boolean isBaggage() {
        return baggage;
    }

    public void setBaggage(boolean baggage) {
        this.baggage = baggage;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", destination='" + destination + '\'' +
                ", seatLevel='" + seatLevel + '\'' +
                ", baggage=" + baggage +
                ", food=" + food +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}
