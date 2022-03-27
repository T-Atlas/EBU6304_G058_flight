package com.app.flight.entity;

/**
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
 */
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

    public Food() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                '}';
    }
}
