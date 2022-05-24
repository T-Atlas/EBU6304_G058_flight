package com.app.flight.service.impl;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.util.Json;

import static com.alibaba.fastjson2.JSON.parseObject;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 * Impl class for getBoardingPass
 */
public class GetBoardingPassImpl implements GetBoardingPass {
    /**
     * lookupBoardingPass from json
     *
     * @return BoardingPass or null
     */
    @Override
    public BoardingPass lookupBoardingPass() {
        BoardingPass boardingPass = new BoardingPass();
        String passengerString = Json.extractJsonData(Json.PASSENGER_JSON_PATH);
        Passenger passenger = parseObject(passengerString, Passenger.class);
        boardingPass.setPassenger(passenger);

        String flightString = Json.extractJsonData(Json.FLIGHT_JSON_PATH);
        Flight flight = parseObject(flightString, Flight.class);
        boardingPass.setFlight(flight);

        String foodString = Json.extractJsonData(Json.FOOD_JSON_PATH);
        Food food = parseObject(foodString, Food.class);
        boardingPass.setFood(food);

        String seatString = Json.extractJsonData(Json.SEAT_JSON_PATH);
        JSONPath rowPath = JSONPath.of("$.row");
        JSONPath colPath = JSONPath.of("$.column");
        String row = (String) rowPath.extract(JSONReader.of(seatString));
        String col = (String) colPath.extract(JSONReader.of(seatString));
        boardingPass.setSeatNo(row + col);

        if (Json.writeJson(Json.BOARDING_PASS_JSON_PATH, boardingPass)) {
            return boardingPass;
        } else {
            return null;
        }
    }
}
