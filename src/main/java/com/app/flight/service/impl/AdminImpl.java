package com.app.flight.service.impl;

import com.app.flight.service.Admin;
import com.app.flight.util.Csv;

import java.util.ArrayList;

public class AdminImpl implements Admin {

    /**
     * View passenger information of each flight by flight ID
     *
     * @param flightId Flight ID
     * @return Passenger information
     */
    @Override
    public ArrayList<String[]> searchCheckedInfoByFlightId(String flightId) {
        ArrayList<String[]> reservationInfo = Csv.readCsv(Csv.RESERVATION_CSV_PATH);
        ArrayList<String[]> boardingPassInfo = Csv.readCsv(Csv.BOARDING_PASS_CSV_PATH);
        ArrayList<String[]> passengerInfo = Csv.readCsv(Csv.PASSENGER_CSV_PATH);
        ArrayList<String[]> checkedInfo = new ArrayList<>();
        for (String[] resInfo : reservationInfo) {
            if (resInfo[2].equals(flightId)) {
                String[] info = new String[6];
                for (String[] pasInfo : passengerInfo) {
                    if (pasInfo[0].equals(resInfo[1])) {
                        info[0] = pasInfo[1] + pasInfo[2];
                    }
                }
                info[1] = resInfo[5];
                info[2] = resInfo[6];
                info[3] = resInfo[7];
                if (resInfo[7].equals("false")) {
                    info[4] = null;
                    info[5] = null;
                } else {
                    for (String[] boaInfo : boardingPassInfo) {
                        if (boaInfo[0].equals(resInfo[1]) && boaInfo[1].equals(resInfo[2])) {
                            info[4] = boaInfo[2];
                            info[5] = boaInfo[3];
                        }
                    }
                }
                checkedInfo.add(info);
            }
        }
        return checkedInfo;
    }

    /**
     * Get all flight ID
     *
     * @return All flight ID
     */
    @Override
    public ArrayList<String> getFlightId() {
        ArrayList<String[]> flightInfo = Csv.readCsv(Csv.FLIGHT_CSV_PATH);
        ArrayList<String> flightId = new ArrayList<>();
        for (String[] flight : flightInfo) {
            flightId.add(flight[0]);
        }
        return flightId;
    }

    /**
     * Query the password of the corresponding ID through the administrator ID
     *
     * @param id Admin ID
     * @return Admin password
     */
    @Override
    public String getPassword(String id) {
        ArrayList<String[]> adminInfo = Csv.readCsv(Csv.ADMIN_CSV_PATH);
        for (String[] adInfo : adminInfo) {
            if (id.equals(adInfo[0])) {
                return adInfo[1];
            }
        }
        return null;
    }

    /**
     * Query the name of the corresponding ID through the administrator ID
     *
     * @param id Admin ID
     * @return Admin name
     */
    @Override
    public String getName(String id) {
        ArrayList<String[]> adminInfo = Csv.readCsv(Csv.ADMIN_CSV_PATH);
        for (String[] adInfo : adminInfo) {
            if (id.equals(adInfo[0])) {
                return adInfo[2];
            }
        }
        return null;
    }
}
