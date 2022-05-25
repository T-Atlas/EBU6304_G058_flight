package com.app.flight.service.impl;

import com.alibaba.fastjson2.JSON;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.entity.Seat;
import com.app.flight.service.GetFlight;
import com.app.flight.service.GetReservation;
import com.app.flight.service.UpdateReservation;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaBoran
 * @author SongBo
 * @version 1.1
 * @date 2022.4.11
 * Impl class for GetReservation
 */
public class GetReservationImpl implements GetReservation, UpdateReservation {
    /**
     * lookupReservation from json
     *
     * @return reservation or null
     */
    public static Reservation lookupReservation() {
        String reservationStr = Json.extractJsonData(Json.RESERVATION_JSON_PATH);
        if (reservationStr != null) {
            List<Reservation> reservations = JSON.parseArray(reservationStr, Reservation.class);
            Flight flight = GetFlightImpl.lookupFlight();
            for (Reservation reservation : reservations) {
                if (flight != null && reservation.getFlight().getFlightId().equals(flight.getFlightId())) {
                    return reservation;
                }
            }
        }
        return null;
    }

    /**
     * lookupReservations by passengerId from Csv
     *
     * @param passengerId passenger ID
     * @return ArrayList<Reservation> reservations
     */
    @Override
    public ArrayList<Reservation> lookupReservations(String passengerId) {
        ArrayList<String[]> csvList = Csv.readCsv(Csv.RESERVATION_CSV_PATH);
        ArrayList<Reservation> reservations = new ArrayList<>();
        boolean flag = false;
        for (String[] csvData : csvList) {
            if (csvData[1].equals(passengerId) && csvData[7].equals("false")) {
                flag = true;
                String[] reservationData = csvData.clone();
                Reservation reservation = new Reservation();
                reservation.setReservationId(reservationData[0]);

                Passenger passenger = new Passenger();
                passenger.setPassengerId(reservationData[1]);
                String pId = passenger.getPassengerId();
                GetPassengerImpl getPassenger = new GetPassengerImpl();
                passenger = getPassenger.lookupPassengerById(pId);
                reservation.setPassenger(passenger);

                Flight flight = new Flight();
                flight.setFlightId(reservationData[2]);
                String fId = flight.getFlightId();
                GetFlight getFlight = new GetFlightImpl();
                flight = getFlight.lookupFlight(fId);
                reservation.setFlight(flight);

                reservation.setSeatLevel(Seat.valueOf(reservationData[3]));
                reservation.setMealsAvailable(Boolean.parseBoolean(reservationData[4]));
                reservation.setHandBaggageNum(Integer.parseInt(reservationData[5]));
                reservation.setCheckedBaggageNum(Integer.parseInt(reservationData[6]));
                reservations.add(reservation);
            }
        }
        if (flag && Json.writeJson(Json.RESERVATION_JSON_PATH, reservations)) {
            System.out.println("Reservation data lookup succeeded");
            return reservations;
        } else {
            System.out.println("Reservation data lookup failed");
            return null;
        }
    }

    /**
     * check whether reservation is looked up,
     *
     * @return true, it is looked up and vice versa
     */
    @Override
    public boolean updateCheckedFlag() {
        Reservation reservation = lookupReservation();
        if (reservation != null) {
            reservation.setChecked(true);
            return Csv.updateCsv(reservation, Csv.RESERVATION_CSV_PATH);
        } else {
            return false;
        }
    }
}

