package com.app.flight.service.temp;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;

/**
 * @author LianJunhong
 */
public class GetPassengerImplTemp implements GetPassenger {
    /**
     * @param id id
     * @return Passenger
     */
    @Override
    public Passenger lookupPassengerById(String id) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);
        passenger.setFirstName("Jun");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        return passenger;
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
