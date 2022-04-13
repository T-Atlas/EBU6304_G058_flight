package com.app.flight.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Food;
import com.app.flight.service.GetFood;
import com.app.flight.util.Csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author 贾博然
 */
public class GetFoodImpl implements GetFood {
    private static final String CSV_PATH = "src/main/resources/com/app/flight/data/csv/Food.csv";
    private static final String JSON_PATH = "src/main/resources/com/app/flight/data/json/Food.json";

    @Override
    public Food getFood(Food.foodType foodName){
        ArrayList<String[]> csvList = Csv.readCsv(CSV_PATH);
        Food food = new Food();
        boolean flag = false;
        for (String[] csvData : csvList){
            if (csvData[0].equals(foodName.name())){
                flag = true;
                String[] foodData = csvData.clone();
                food.setFoodName(Food.foodType.valueOf(foodData[0]));
                food.setFoodPrice(Double.parseDouble(foodData[1]));
                System.out.println("数据查找成功");
            }

        }
        if (flag){
            try (FileWriter foodJson = new FileWriter(JSON_PATH); PrintWriter out = new PrintWriter(foodJson)) {
                String foodString = JSON.toJSONString(food, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
                out.write(foodString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return food;
        }
        else {
            System.out.println("数据查找失败");
            return null;
        }
    }

    public static void main(String[] args){

        GetFoodImpl getFood = new GetFoodImpl();
        Food food = getFood.getFood(Food.foodType.HALAL);
        System.out.println(food);

    }
}
