package com.app.flight.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(ordinal = 1)
    private foodType foodName;

    /**
     * Price of each food.
     */
    @JSONField(ordinal = 2)
    private double foodPrice;

    public enum foodType {
        /**
         * Three types of food
         */
        STANDARD, HALAL, VEGETARIAN
    }
}
