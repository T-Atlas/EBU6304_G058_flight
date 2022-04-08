package com.app.flight.entity;

import lombok.Data;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.3.27
 */
@Data
public class Tag {

    /**
     * Serial number of the tag
     */
    private String tagNo;

    /**
     * Number of hand baggage
     */
    private int handBaggageNum;

    /**
     * Serial number of hand baggage
     */
    private int baggageNo;

}
