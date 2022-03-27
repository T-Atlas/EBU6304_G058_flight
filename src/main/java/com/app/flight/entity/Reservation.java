package com.app.flight.entity;

/**
 * @author LianJunhong
 */
public class Reservation {

    /**
     * The unique identification of particular reservation.
     */
    private String reservationId;

    private int passengerId;
    private String flightId;
    private String seatLevel;
    private boolean foodReserved;
    private int handBaggageNum;
    private int checkedBaggageNum;

    public enum seatClass {
        /**
         * Three types of aircraft accommodation
         */
        ECONOMY_CLASS, BUSINESS_CLASS, FIRST_CLASS
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getSeatLevel() {
        return seatLevel;
    }

    public void setSeatLevel(String seatLevel) {
        this.seatLevel = seatLevel;
    }

    public boolean isFoodReserved() {
        return foodReserved;
    }

    public void setFoodReserved(boolean foodReserved) {
        this.foodReserved = foodReserved;
    }

    public int getHandBaggageNum() {
        return handBaggageNum;
    }

    public void setHandBaggageNum(int handBaggageNum) {
        this.handBaggageNum = handBaggageNum;
    }

    public int getCheckedBaggageNum() {
        return checkedBaggageNum;
    }

    public void setCheckedBaggageNum(int checkedBaggageNum) {
        this.checkedBaggageNum = checkedBaggageNum;
    }
}
