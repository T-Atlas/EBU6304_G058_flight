package com.app.flight.service.impl;

import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;

import java.util.ArrayList;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.6
 */
public class GetPassengerImpl implements GetPassenger {

    @Override
    public Passenger lookupPassengerById(String passengerId) {
        ArrayList<String[]> csvList = Csv.readCsv(Csv.PASSENGER_CSV_PATH);
        for (String[] csvData : csvList) {
            if (csvData[0].equals(passengerId)) {
                String[] passengerData = csvData.clone();
                Passenger passenger = new Passenger();
                passenger.setPassengerId(passengerData[0]);
                passenger.setLastName(passengerData[1]);
                passenger.setFirstName(passengerData[2]);
                passenger.setAge(Integer.parseInt(passengerData[3]));
                passenger.setTelephone(passengerData[4]);
                Json.writeJson(Json.PASSENGER_JSON_PATH, passenger);
                return passenger;
            }
        }
        return null;
    }

    @Override
    public Passenger lookupPassengerByBookingNumber(String bookNumber) {
        ArrayList<String[]> csvList = Csv.readCsv(Csv.RESERVATION_CSV_PATH);
        for (String[] csvData : csvList) {
            if (csvData[0].equals(bookNumber)) {
                return lookupPassengerById(csvData[1]);
            }
        }
        return null;
    }
}
