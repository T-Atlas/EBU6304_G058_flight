package com.app.flight.service;

import com.app.flight.entity.Food;

/**
 * @author 贾博然
 */
public interface SetFood {

    /**
     * Method to get food
     *
     * @param foodName food type
     */
    void setFood(Food.foodType foodName);
}
