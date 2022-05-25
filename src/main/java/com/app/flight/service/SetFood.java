package com.app.flight.service;

import com.app.flight.entity.Food;
import com.app.flight.service.impl.SetFoodImpl;

/**
 * @author Jia Boran
 * @version 1.0
 * @see SetFoodImpl
 */
public interface SetFood {

    /**
     * Method to get food
     *
     * @param foodName food type
     */
    void setFood(Food.foodType foodName);
}
