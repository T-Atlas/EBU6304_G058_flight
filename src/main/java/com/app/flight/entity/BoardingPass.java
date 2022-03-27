package com.app.flight.entity;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class BoardingPass {
    private Passenger passenger;
    private Flight flight;
    private String seatNumber;
    private String foodType;
    private int boardingNo;

    public BoardingPass() {
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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getBoardingNo() {
        return boardingNo;
    }

    public void setBoardingNo(int boardingNo) {
        this.boardingNo = boardingNo;
    }

    @Override
    public String toString() {
        return "BoardingPass{" +
                "passenger=" + passenger +
                ", flight=" + flight +
                ", seatNumber='" + seatNumber + '\'' +
                ", foodType='" + foodType + '\'' +
                ", boardingNo=" + boardingNo +
                '}';
    }
}
