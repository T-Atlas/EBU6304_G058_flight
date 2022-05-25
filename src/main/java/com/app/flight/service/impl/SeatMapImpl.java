package com.app.flight.service.impl;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.app.flight.service.GetSeatMap;
import com.app.flight.service.SetSeatMap;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;
import com.app.flight.util.SeatUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SongBo
 * @author LianJunhong
 * @version 1.1
 * @date 2022.4.22
 */
public class SeatMapImpl implements SetSeatMap, GetSeatMap {

    /**
     * This method is used to get the seat price.
     *
     * @return the price of the seat in double.
     */
    public static double lookupSeatPrice() {
        String seatStr = Json.extractJsonData(Json.SEAT_JSON_PATH);
        if (seatStr != null) {
            JSONPath pricePath = JSONPath.of("$.price");
            return Double.parseDouble((String) pricePath.extract(JSONReader.of(seatStr)));
        } else {
            return 0.0;
        }
    }

    /**
     * This method is used to update the seat map.
     *
     * @param flightId flightId
     * @param column   Modify the number of columns of seats
     * @param row      Modify the number of rows of seats
     * @param price    Modify the price of the selected seat
     */
    @Override
    public void updateSeatMap(String flightId, String column, int row, double price) {
        Map<Integer, Map<String, Boolean>> seatMap = lookupSeatMap(flightId);
        Map<String, Boolean> stringBooleanMap = seatMap.get(row);
        Boolean status = stringBooleanMap.get(column);
        status = !status;
        stringBooleanMap.replace(column, status);
        seatMap.replace(row, stringBooleanMap);
        String[] columnString = new String[]{"A", "B", "C", "D", "E", "F"};
        SeatUtil.writeSeatMap(SeatUtil.generateSeatFilePath(flightId), seatMap, columnString);

        try (FileWriter seatJson = new FileWriter(Json.SEAT_JSON_PATH); PrintWriter out = new PrintWriter(seatJson)) {
            String seatInfo = "{\n" +
                    "\t\"flightId\":\"" + flightId + "\",\n" +
                    "\t\"column\":\"" + column + "\",\n" +
                    "\t\"row\":\"" + row + "\",\n" +
                    "\t\"price\":\"" + price + "\"\n" +
                    "}";
            out.write(seatInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the seat map.
     *
     * @param flightId Flight No.
     * @return the seat map in Map<Integer, Map<String, Boolean>>
     */
    @Override
    public Map<Integer, Map<String, Boolean>> lookupSeatMap(String flightId) {
        ArrayList<String[]> seatData = Csv.readCsv(SeatUtil.generateSeatFilePath(flightId));
        Map<Integer, Map<String, Boolean>> seatMap = new HashMap<>(seatData.size());
        String[] columnString = new String[]{"A", "B", "C", "D", "E", "F"};
        int j = 1;
        for (String[] seatStatus : seatData) {
            Map<String, Boolean> rowMap = new HashMap<>(6);
            for (int i = 0; i < seatStatus.length; i++) {
                rowMap.put(columnString[i], Boolean.valueOf(seatStatus[i]));
            }
            seatMap.put(j, rowMap);
            j++;
        }
        return seatMap;
    }
}
