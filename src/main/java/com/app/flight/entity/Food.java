package com.app.flight.entity;

import lombok.Data;

/**
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
 */
@Data
public class Food {

    /**
     * Name of each food.
     */
    private String foodName;

    /**
     * Price of each food.
     */
    private double foodPrice;

    public enum foodType {
        /**
         * Three types of food
         */
        STANDARD, HALAL, VEGETARIAN
    }
}
