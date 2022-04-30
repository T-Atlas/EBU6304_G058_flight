package com.app.flight.service.impl;

import com.app.flight.service.SetSeatMap;
import com.app.flight.util.Json;
import com.app.flight.util.Seat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public class SetSeatMapImpl implements SetSeatMap {
    @Override
    public void updateSeatMap(String flightId, String column, int row) {
        GetSeatMapImpl getSeatMap = new GetSeatMapImpl();
        Map<Integer, Map<String, Boolean>> seatMap = getSeatMap.lookupSeatMap(flightId);
        Map<String, Boolean> stringBooleanMap = seatMap.get(row);
        Boolean status = stringBooleanMap.get(column);
        status = !status;
        stringBooleanMap.replace(column, status);
        seatMap.replace(row, stringBooleanMap);
        String[] columnString = new String[]{"A", "B", "C", "D", "E", "F"};
        Seat.writeSeatMap(Seat.generateSeatFilePath(flightId), seatMap, columnString);

        try (FileWriter seatJson = new FileWriter(Json.SEAT_JSON_PATH); PrintWriter out = new PrintWriter(seatJson)) {
            String seatInfo = "{\n" +
                    "\t\"flightId\":\"" + flightId + "\",\n" +
                    "\t\"column\":\"" + column + "\",\n" +
                    "\t\"row\":\"" + row + "\"\n" +
                    "}";
            out.write(seatInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
