package com.app.flight.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public class Json {
    public static final String BOARDING_PASS_JSON_PATH = "data/json/BoardingPass.json";
    public static final String FLIGHT_JSON_PATH = "data/json/Flight.json";
    public static final String FOOD_JSON_PATH = "data/json/Food.json";
    public static final String PASSENGER_JSON_PATH = "data/json/Passenger.json";
    public static final String RESERVATION_JSON_PATH = "data/json/Reservation.json";
    public static final String SEAT_JSON_PATH = "data/json/Seat.json";
    public static final String TYPE_JSON_PATH = "data/json/Type.json";

    public static boolean writeJson(String jsonFilePath, Object obj) {
        try (FileWriter jsonWriter = new FileWriter(jsonFilePath); PrintWriter out = new PrintWriter(jsonWriter)) {
            String objString = JSON.toJSONString(obj, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.WriteEnumsUsingName);
            out.write(objString);
            out.close();
            jsonWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String extractJsonData(String jsonFilePath) {
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Path.of(jsonFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
