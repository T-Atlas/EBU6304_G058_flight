package com.app.fight.entity;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Tag {
    private Passenger passenger;
    private Flight flight;
    private int tagNo;
    private int baggageNum;
    private int baggageId;
    private double weight;

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

    public int getBaggageNum() {
        return baggageNum;
    }

    public void setBaggageNum(int baggageNum) {
        this.baggageNum = baggageNum;
    }

    public int getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(int baggageId) {
        this.baggageId = baggageId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "passenger=" + passenger +
                ", flight=" + flight +
                ", tagNo=" + tagNo +
                ", baggageNum=" + baggageNum +
                ", baggageId=" + baggageId +
                ", weight=" + weight +
                '}';
    }
}
