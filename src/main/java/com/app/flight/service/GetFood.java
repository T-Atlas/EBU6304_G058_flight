package com.app.flight.service;

import com.app.flight.entity.Food;

/**
 * @author 贾博然
 */
public interface GetFood {

    /**
     * Method to get food
     *
     * @param foodName
     * @return
     */
    Food getFood(Food.foodType foodName);
}
