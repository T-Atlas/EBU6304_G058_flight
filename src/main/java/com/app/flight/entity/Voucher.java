package com.app.flight.entity;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.26
 */
public class Voucher {
    private Passenger passenger;
    private Flight flight;
    private int voucherId;
    private int counterNo;

    public Voucher() {
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

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getCounterNo() {
        return counterNo;
    }

    public void setCounterNo(int counterNo) {
        this.counterNo = counterNo;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "passenger=" + passenger +
                ", flight=" + flight +
                ", voucherId=" + voucherId +
                ", counterNo=" + counterNo +
                '}';
    }
}
