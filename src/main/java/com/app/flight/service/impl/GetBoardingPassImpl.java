package com.app.flight.service.impl;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.alibaba.fastjson2.JSON.parseObject;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public class GetBoardingPassImpl implements GetBoardingPass {

    private static String extractJsonData(File file) {
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Override
    public BoardingPass lookupBoardingPass() {
        BoardingPass boardingPass = new BoardingPass();
        File passengerFile = new File(Json.PASSENGER_JSON_PATH);
        String passengerString = extractJsonData(passengerFile);
        Passenger passenger = parseObject(passengerString, Passenger.class);
        boardingPass.setPassenger(passenger);

        File flightFile = new File(Json.FLIGHT_JSON_PATH);
        String flightString = extractJsonData(flightFile);
        Flight flight = parseObject(flightString, Flight.class);
        boardingPass.setFlight(flight);

        File foodFile = new File(Json.FOOD_JSON_PATH);
        String foodString = extractJsonData(foodFile);
        Food food = parseObject(foodString, Food.class);
        boardingPass.setFood(food);

        File seatFile = new File(Json.SEAT_JSON_PATH);
        String seatString = extractJsonData(seatFile);
        JSONPath rowPath = JSONPath.of("$.row");
        JSONPath colPath = JSONPath.of("$.column");
        String row = (String) rowPath.extract(JSONReader.of(seatString));
        String col = (String) colPath.extract(JSONReader.of(seatString));
        boardingPass.setSeatNo(row + col);

        if (Json.writeJson(Json.BOARDING_PASS_JSON_PATH, boardingPass) && Csv.addCsv(boardingPass, Csv.BOARDING_PASS_CSV_PATH, false)) {
            return boardingPass;
        } else {
            return null;
        }

    }
}
