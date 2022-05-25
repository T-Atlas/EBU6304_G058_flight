package com.app.flight.service.impl;

import com.alibaba.fastjson2.JSON;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;

import java.util.ArrayList;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.6
 * Impl class for GetPassenger
 */
public class GetPassengerImpl implements GetPassenger {

    /**
     * lookupPassenger from json
     *
     * @return passenger or null
     */
    public static Passenger lookupPassenger() {
        String passengerStr = Json.extractJsonData(Json.PASSENGER_JSON_PATH);
        if (passengerStr != null) {
            return JSON.parseObject(passengerStr, Passenger.class);
        } else {
            return null;
        }
    }

    /**
     * lookupPassengerById form Csv by id
     *
     * @param passengerId id
     * @return passenger or null
     */
    @Override
    public Passenger lookupPassengerById(String passengerId) {
        ArrayList<String[]> csvList = Csv.readCsv(Csv.PASSENGER_CSV_PATH);
        for (String[] csvData : csvList) {
            if (csvData[0].equals(passengerId)) {
                String[] passengerData = csvData.clone();
                Passenger passenger = new Passenger();
                passenger.setPassengerId(passengerData[0]);
                passenger.setFirstName(passengerData[1]);
                passenger.setLastName(passengerData[2]);
                passenger.setAge(Integer.parseInt(passengerData[3]));
                passenger.setTelephone(passengerData[4]);
                if (Json.writeJson(Json.PASSENGER_JSON_PATH, passenger)) {
                    System.out.println("Passenger data found successfully");
                    return passenger;
                }
            }
        }
        System.out.println("Passenger data search failed");
        return null;
    }

    /**
     * lookupPassengerByBookingNumber by bookNumber from Csv
     *
     * @param bookNumber id
     * @return passenger or null
     */
    @Override
    public Passenger lookupPassengerByBookingNumber(String bookNumber) {
        ArrayList<String[]> csvList = Csv.readCsv(Csv.RESERVATION_CSV_PATH);
        for (String[] csvData : csvList) {
            if (csvData[0].equals(bookNumber)) {
                return lookupPassengerById(csvData[1]);
            }
        }
        System.out.println("Passenger data search failed");
        return null;
    }
}
