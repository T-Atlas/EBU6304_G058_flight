package com.app.fight.entity;

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
    private int boardingGate;
    private int boardingNo;

    public enum typeOfMeal {
        //Three types of meal
        STANDARD, VEGETARIAN, HALAL
    }

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

    public int getBoardingGate() {
        return boardingGate;
    }

    public void setBoardingGate(int boardingGate) {
        this.boardingGate = boardingGate;
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
                ", boardingGate=" + boardingGate +
                ", boardingNo=" + boardingNo +
                '}';
    }
}