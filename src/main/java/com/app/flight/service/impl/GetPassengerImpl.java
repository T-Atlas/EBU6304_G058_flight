package com.app.flight.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.util.Csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.6
 */
public class GetPassengerImpl implements GetPassenger {
    private static final String CSV_PATH = "src/main/resources/com/app/flight/data/csv/Passenger.csv";
    private static final String JSON_PATH = "src/main/resources/com/app/flight/data/json/Passenger.json";
    @Override
    public Passenger lookupPassengerById(String id) {
        ArrayList<String[]> csvList = Csv.readCsv(CSV_PATH);
        for (String[] csvData : csvList) {
            if (csvData[0].equals(id)) {
                String[] passengerData = csvData.clone();
                Passenger passenger = new Passenger();
                passenger.setPassengerId(passengerData[0]);
                passenger.setLastName(passengerData[1]);
                passenger.setFirstName(passengerData[2]);
                passenger.setAge(Integer.parseInt(passengerData[3]));
                passenger.setTelephone(passengerData[4]);
                try (FileWriter passengerJson = new FileWriter(JSON_PATH); PrintWriter out = new PrintWriter(passengerJson)) {
                    String passengerString = JSON.toJSONString(passenger, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
                    out.write(passengerString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return passenger;
            }
        }
        return null;
    }

    /**
     * @param bookNumber id
     * @return Passenger
     */
    @Override
    public Passenger lookupPassengerByBookingNumber(String bookNumber) {
        return null;
    }
}
