package com.app.flight.entity;

import lombok.Data;

/**
 * @author SongBo
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
 */
@Data
public class BoardingPass {

    /**
     * Serial number of the boarding pass.
     */
    private String boardingNo;

    /**
     * Seat number with a total of three characters.
     * The first two characters are numbers and the last character is letters.
     */
    private String seatNo;

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

}
