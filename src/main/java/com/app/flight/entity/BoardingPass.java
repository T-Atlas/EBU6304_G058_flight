package com.app.flight.entity;

/**
 * @author SongBo
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
 */
public class BoardingPass {

    /**
     * Seat number with a total of three characters.
     * The first two characters are numbers and the last character is letters.
     */
    private String seatNo;

    /**
     * Serial number of the boarding pass.
     */
    private int boardingNo;

    /**
     * The passenger for the boarding pass.
     */
    private Passenger passenger;

    /**
     * The flight information for the boarding pass.
     */
    private Flight flight;

    /**
     * The food information for the boarding pass.
     */
    private Food food;

    public BoardingPass() {
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public int getBoardingNo() {
        return boardingNo;
    }

    public void setBoardingNo(int boardingNo) {
        this.boardingNo = boardingNo;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "BoardingPass{" +
                "seatNo='" + seatNo + '\'' +
                ", boardingNo=" + boardingNo +
                ", passenger=" + passenger.getPassengerId() +
                ", flight=" + flight.getFlightId() +
                ", food=" + food +
                '}';
    }
}
