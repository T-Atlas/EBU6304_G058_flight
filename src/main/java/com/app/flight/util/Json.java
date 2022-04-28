package com.app.flight.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public class Json {
    public static final String BOARDING_PASS_JSON_PATH = "src/main/resources/com/app/flight/data/json/BoardingPass.json";
    public static final String FLIGHT_JSON_PATH = "src/main/resources/com/app/flight/data/json/Flight.json";
    public static final String FOOD_JSON_PATH = "src/main/resources/com/app/flight/data/json/Food.json";
    public static final String PASSENGER_JSON_PATH = "src/main/resources/com/app/flight/data/json/Passenger.json";
    public static final String RESERVATION_JSON_PATH = "src/main/resources/com/app/flight/data/json/Reservation.json";
    public static final String SEAT_JSON_PATH = "src/main/resources/com/app/flight/data/json/Seat.json";

    public static boolean writeJson(String jsonFilePath, Object obj) {
        try (FileWriter jsonWriter = new FileWriter(jsonFilePath); PrintWriter out = new PrintWriter(jsonWriter)) {
            String objString = JSON.toJSONString(obj, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.FieldBased,
                    JSONWriter.Feature.WriteEnumsUsingName);
            out.write(objString);
            out.close();
            jsonWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
