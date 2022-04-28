package com.app.flight.service.impl;

import com.app.flight.entity.Flight;
import com.app.flight.service.GetFlight;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Jia Boran
 * @version 1.0
 * @date 2022.4.11
 */
public class GetFlightImpl implements GetFlight {

    @Override
    public Flight lookupFlight(String flightId) {
        ArrayList<String[]> csvList = Csv.readCsv(Csv.FLIGHT_CSV_PATH);
        Flight flight = new Flight();
        boolean flag = false;
        for (String[] csvData : csvList) {
            if (csvData[0].equals(flightId)) {
                flag = true;
                String[] flightData = csvData.clone();
                flight.setFlightId(flightData[0]);
                flight.setDeparture(flightData[1]);
                flight.setDestination(flightData[2]);
                flight.setBoardingGate(flightData[3]);

                String strLocalDate1 = flightData[4];
                DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime localDateTime1 = LocalDateTime.parse(strLocalDate1, fmt1);
                flight.setBoardingTime(localDateTime1);

                String strLocalDate2 = flightData[5];
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime localDateTime2 = LocalDateTime.parse(strLocalDate2, fmt2);
                flight.setDepartureTime(localDateTime2);

                String strLocalDate3 = flightData[6];
                DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime localDateTime3 = LocalDateTime.parse(strLocalDate3, fmt3);
                flight.setArrivalTime(localDateTime3);
            }
        }
        if (flag) {
            Json.writeJson(Json.FLIGHT_JSON_PATH, flight);
            System.out.println("flight数据查找成功");
            return flight;
        } else {
            System.out.println("flight数据查找失败");
            return null;
        }
    }
}
