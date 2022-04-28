package com.app.flight.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.3.27
 */
@Data
public class Passenger {

    /**
     * The unique identification of particular passenger.
     */
    @JSONField(ordinal = 1)
    private String passengerId;

    /**
     * The first name of passenger.
     */
    @JSONField(ordinal = 2)
    private String firstName;

    /**
     * The last name of passenger.
     */
    @JSONField(ordinal = 3)
    private String lastName;

    /**
     * The age of passenger.
     */
    @JSONField(ordinal = 4)
    private int age;

    /**
     * The telephone of passenger.
     */
    @JSONField(ordinal = 5)
    private String telephone;

}
