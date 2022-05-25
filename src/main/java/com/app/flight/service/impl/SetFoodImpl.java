package com.app.flight.service.impl;

import com.alibaba.fastjson2.JSON;
import com.app.flight.entity.Food;
import com.app.flight.service.SetFood;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;

import java.util.ArrayList;

/**
 * @author JiaBoran
 */
public class SetFoodImpl implements SetFood {

    /**
     * This method is used to get food data from json file
     *
     * @return food data
     */
    public static Food lookupFood() {
        String foodStr = Json.extractJsonData(Json.FOOD_JSON_PATH);
        if (foodStr != null) {
            return JSON.parseObject(foodStr, Food.class);
        } else {
            return null;
        }
    }

    /**
     * This method is used to set food data to csv file
     *
     * @param foodName food type
     */
    @Override
    public void setFood(Food.foodType foodName) {
        ArrayList<String[]> csvList = Csv.readCsv(Csv.FOOD_CSV_PATH);
        Food food = new Food();
        for (String[] csvData : csvList) {
            if (csvData[0].equals(foodName.name())) {
                String[] foodData = csvData.clone();
                food.setFoodName(Food.foodType.valueOf(foodData[0]));
                food.setFoodPrice(Double.parseDouble(foodData[1]));
                Json.writeJson(Json.FOOD_JSON_PATH, food);
                System.out.println("Food data search succeeded");
                return;
            }
        }
        System.out.println("Food data search failed");
    }
}
