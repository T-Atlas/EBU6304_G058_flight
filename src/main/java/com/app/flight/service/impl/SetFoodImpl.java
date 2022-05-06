package com.app.flight.service.impl;

import com.app.flight.entity.Food;
import com.app.flight.service.SetFood;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;

import java.util.ArrayList;

public class SetFoodImpl implements SetFood {
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
                System.out.println("food数据查找成功");
                return;
            }
        }
        System.out.println("food数据查找失败");
    }
}
