package com.app.flight.service.impl;

import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetBoardingPass;
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

    private static String nioMethod(File file) {
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
        String passengerString = nioMethod(passengerFile);
        Passenger passenger = parseObject(passengerString, Passenger.class);
        boardingPass.setPassenger(passenger);

        File flightFile = new File(Json.FLIGHT_JSON_PATH);
        String flightString = nioMethod(flightFile);
        Flight flight = parseObject(flightString, Flight.class);
        boardingPass.setFlight(flight);

        File foodFile = new File(Json.FOOD_JSON_PATH);
        String foodString = nioMethod(foodFile);
        Food food = parseObject(foodString, Food.class);
        boardingPass.setFood(food);

        File seatFile = new File(Json.SEAT_JSON_PATH);
        String seatString = nioMethod(seatFile);
        String replace = seatString.replace("]", "");
        String[] split = replace.split(",");
        boardingPass.setSeatNo(split[2].replaceAll("\"", "") + split[1].replaceAll("\"", ""));
        Json.writeJson(Json.BOARDING_PASS_JSON_PATH, boardingPass);
        return boardingPass;
    }
}
