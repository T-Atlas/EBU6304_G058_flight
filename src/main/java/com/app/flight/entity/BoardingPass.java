package com.app.flight.entity;

import com.alibaba.fastjson2.annotation.JSONField;
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
     * The passenger for the boarding pass.
     */
    @JSONField(ordinal = 1)
    private Passenger passenger;

    /**
     * Seat number with a total of three characters.
     * The first two characters are numbers and the last character is letters.
     */
    @JSONField(ordinal = 2)
    private String seatNo;

    /**
     * The flight information for the boarding pass.
     */
    @JSONField(ordinal = 3)
    private Flight flight;

    /**
     * The food information for the boarding pass.
     */
    @JSONField(ordinal = 4)
    private Food food;

}
