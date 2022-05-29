package com.app.flight.service.temp;

import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetReservation;
import com.app.flight.service.impl.GetReservationImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is the temporary class for implement the GetReservationImp class.
 *
 * @author LianJunhong
 * @deprecated This implement method has been deprecated.
 * Use the {@link GetReservationImpl}class instead.
 */
@Deprecated
public class GetReservationImplTemp implements GetReservation {

    /**
     * This method is used to get the reservation by the reservation id.
     * @param passengerId passenger ID
     * @return the reservation
     * @deprecated This implement method has been deprecated.
     * Use the {@link GetReservationImpl}class instead.
     */
    @Override
    @Deprecated
    public ArrayList<Reservation> lookupReservations(String passengerId) {

        Passenger passenger = new Passenger();
        passenger.setPassengerId(passengerId);
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
