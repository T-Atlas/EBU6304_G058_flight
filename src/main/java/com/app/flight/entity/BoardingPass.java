package com.app.flight.entity;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class BoardingPass {
    private Passenger passenger;
    private Flight flight;
    /**
     * Seat number with a total of three characters.
     * The first two characters are numbers and the last character is letters.
     */
    private String seatNo;
    private Food food;
    private int boardingNo;

    public BoardingPass() {
    }


}
