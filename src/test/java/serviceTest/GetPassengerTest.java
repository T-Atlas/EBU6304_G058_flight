package serviceTest;

import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPassengerTest {
    @Test
    public void lookupPassengerTest() {
        String id = "220802200005217748";
        GetPassenger getPassenger = new GetPassengerImpl();
        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);

        assertEquals(passenger, getPassenger.lookupPassengerById(id));

        String bookNumber = "1517539047050973184";
        //System.out.println(getPassenger.lookupPassengerByBookingNumber(bookNumber));
        Reservation reservation1 = new Reservation();
        reservation1.setReservationId(bookNumber);

        assertEquals(reservation1.getPassenger(), getPassenger.lookupPassengerByBookingNumber(bookNumber));

        /*String s = JSON.toJSONString(passenger, JSONWriter.Feature.PrettyFormat);
        //System.out.println(s);
        assertEquals(s,JSON.toJSONString(passenger, JSONWriter.Feature.PrettyFormat));*/
    }
}
