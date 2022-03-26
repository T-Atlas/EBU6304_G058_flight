package com.app.flight.entity;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Tag {
    private Passenger passenger;
    private Flight flight;
    private int tagNo;

    public Tag() {
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

    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "passenger=" + passenger +
                ", flight=" + flight +
                ", tagNo=" + tagNo +
                '}';
    }
}
