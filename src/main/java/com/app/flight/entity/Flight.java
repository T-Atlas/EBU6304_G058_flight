package com.app.flight.entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Flight {
    private Passenger passenger;
    private int bookingNumber;
    /**
     * flightId前两个为字母，后四位为数字
     */
    private String flightId;
    private String departureStation;
    private String destination;
    private String seatLevel;
    private int carryOnBaggage;
    private int checkInBaggage;
    private boolean food;
    private LocalTime time;
    private LocalDate date;

    public enum seatClass {
        //Three types of aircraft accommodation
        ECONOMY_CLASS, BUSINESS_CLASS, First_CLASS
    }

    public Flight() {
    }

    @Override
    public String toString() {
        return "Flight{" +
                "passenger=" + passenger +
                ", bookingNumber=" + bookingNumber +
                ", flightId='" + flightId + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", destination='" + destination + '\'' +
                ", seatLevel='" + seatLevel + '\'' +
                ", carryOnBaggage=" + carryOnBaggage +
                ", checkInBaggage=" + checkInBaggage +
                ", food=" + food +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}
