package serviceTest;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.entity.Seat;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPassengerTest {
    /**
     * Test for looking up passenger by id and bookNumber and json
     */
    @Test
    public void lookupPassengerTest() {
        String id = "123456";
        GetPassenger getPassenger = new GetPassengerImpl();
        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);
        passenger.setFirstName("Test");
        passenger.setLastName("Jun");
        passenger.setAge(22);
        passenger.setTelephone("13104368848");
        assertEquals(passenger, getPassenger.lookupPassengerById(id));

        Flight flight = new Flight();
        flight.setFlightId("MH8633");
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("B08");
        flight.setBoardingTime(LocalDateTime.of(2022, 9, 11, 7, 5));
        flight.setDepartureTime(LocalDateTime.of(2022, 9, 11, 9, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 9, 11, 12, 55));

        String bookNumber = "1517539047050973184";
        Reservation reservation = new Reservation();
        reservation.setReservationId(bookNumber);
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setSeatLevel(Seat.FIRST_CLASS);
        reservation.setMealsAvailable(true);
        reservation.setHandBaggageNum(0);
        reservation.setCheckedBaggageNum(1);
        assertEquals(reservation.getPassenger(), getPassenger.lookupPassengerByBookingNumber(bookNumber));

        String s = JSON.toJSONString(passenger, JSONWriter.Feature.PrettyFormat);
        assertEquals(s, JSON.toJSONString(passenger, JSONWriter.Feature.PrettyFormat));
    }
}
