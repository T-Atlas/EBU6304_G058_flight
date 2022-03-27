package com.app.flight.entity;

/**
 * @author LianJunhong
 */
public class Food {

    private String foodName;
    private double foodPrice;

    public enum foodType {
        /**
         * Three types of food
         */
        STANDARD, HALAL, VEGETARIAN
    }
}
