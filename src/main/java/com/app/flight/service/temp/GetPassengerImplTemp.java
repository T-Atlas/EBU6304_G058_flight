package com.app.flight.service.temp;

import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;

/**
 * This class is the temporary class for implement the GetPassenger class.
 *
 * @author LianJunhong
 * @deprecated This implement method has been deprecated.
 * Use the {@link GetPassengerImpl}class instead.
 */
@Deprecated
public class GetPassengerImplTemp implements GetPassenger {
    /**
     * This method is used to get the passenger by the passenger's id.
     * @param passengerId id
     * @return Passenger
     * @deprecated This implement method has been deprecated.
     * Use the {@link GetPassengerImpl}class instead.
     */
    @Override
    @Deprecated
    public Passenger lookupPassengerById(String passengerId) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(passengerId);
        passenger.setFirstName("Jun");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        return passenger;
    }

    /**
     * This method is used to get the passenger by the passenger's name.
     * @param bookNumber bookNumber
     * @return Passenger
     * @deprecated This implement method has been deprecated.
     * Use the {@link GetPassengerImpl}class instead.
     */
    @Override
    @Deprecated
    public Passenger lookupPassengerByBookingNumber(String bookNumber) {
        return null;
    }
}
