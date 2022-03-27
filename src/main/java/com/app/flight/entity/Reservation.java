package com.app.flight.entity;

/**
 * @author LianJunhong
 * @author SongBo
 * @version 1.0
 * @date 2022.3.27
 */
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

    public Reservation() {
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getSeatLevel() {
        return seatLevel;
    }

    public void setSeatLevel(String seatLevel) {
        this.seatLevel = seatLevel;
    }

    public boolean isMealsAvailable() {
        return mealsAvailable;
    }

    public void setMealsAvailable(boolean mealsAvailable) {
        this.mealsAvailable = mealsAvailable;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", passenger=" + passenger.getPassengerId() +
                ", flight='" + flight.getFlightId() + '\'' +
                ", seatLevel='" + seatLevel + '\'' +
                ", mealsAvailable=" + mealsAvailable +
                ", handBaggageNum=" + handBaggageNum +
                ", checkedBaggageNum=" + checkedBaggageNum +
                '}';
    }
}
