package com.app.flight.service.temp;

import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.GetReservation;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author LianJunhong
 */
public class GetPassengerImplTemp implements GetReservation {
    /*@Override
    public Passenger lookupPassenger(String id) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);
        passenger.setFirstName("Jun");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        return passenger;
    }*/

    @Override
    public ArrayList<Reservation> lookupReservations(String id) {

        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);
        passenger.setFirstName("Jun");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);

        Flight f = new Flight();
        f.setDeparture("Haikou");
        f.setDestination("Beijing");
        f.setDepartureTime(LocalDateTime.now());
        f.setFlightId("1");

        Reservation r = new Reservation();
        r.setPassenger(passenger);
        r.setFlight(f);
        r.setReservationId("1234");
        r.setCheckedBaggageNum(1);
        r.setMealsAvailable(true);
        r.setHandBaggageNum(2);

        ArrayList<Reservation> rList = new ArrayList<>();
        rList.add(r);

        return rList;
    }
}
