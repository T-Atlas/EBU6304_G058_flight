package serviceTest;

import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.entity.Seat;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import com.app.flight.util.Csv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaBoran
 * @version 2.1
 * Test class for getting passenger
 */
public class GetPassengerTest {
    static String id = "130681200104296464";
    static String bookNumber = "1517539047050973184";
    static Passenger passenger = new Passenger();
    static Flight flight = new Flight();
    static Reservation reservation = new Reservation();

    /**
     * Before all tests initiation of creating new data adding to Csv
     */
    @BeforeAll
    public static void init() {
        passenger.setPassengerId(id);
        passenger.setFirstName("Test");
        passenger.setLastName("Jun");
        passenger.setAge(22);
        passenger.setTelephone("13104368848");
        Csv.addCsv(passenger, Csv.PASSENGER_CSV_PATH, true);

        flight.setFlightId("MH8638");
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("B08");
        flight.setBoardingTime(LocalDateTime.of(2022, 9, 11, 7, 5));
        flight.setDepartureTime(LocalDateTime.of(2022, 9, 11, 9, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 9, 11, 12, 55));
        Csv.addCsv(flight, Csv.FLIGHT_CSV_PATH, true);

        reservation.setReservationId(bookNumber);
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setSeatLevel(Seat.FIRST_CLASS);
        reservation.setMealsAvailable(true);
        reservation.setHandBaggageNum(0);
        reservation.setCheckedBaggageNum(1);
        Csv.addCsv(reservation, Csv.RESERVATION_CSV_PATH, false);
    }

    /**
     * After all tests deleting all new data from Csv
     */
    @AfterAll
    public static void clear() {
        Csv.deleteCsv(passenger, Csv.PASSENGER_CSV_PATH, true);
        Csv.deleteCsv(flight, Csv.FLIGHT_CSV_PATH, true);
        Csv.deleteCsv(reservation, Csv.RESERVATION_CSV_PATH, true);
    }

    /**
     * Testing lookupPassengerById and lookupPassengerByBookingNumber function
     */
    @Test
    public void lookupPassengerTest() {
        GetPassenger getPassenger = new GetPassengerImpl();
        assertEquals(passenger, getPassenger.lookupPassengerById(id));
        assertEquals(reservation.getPassenger(), getPassenger.lookupPassengerByBookingNumber(bookNumber));
    }
}
