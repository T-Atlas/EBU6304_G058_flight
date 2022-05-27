package serviceTest;

import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.entity.Seat;
import com.app.flight.service.GetReservation;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.util.Csv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaBoran
 * @version 2.0
 * Test class for getting reservations
 */
public class GetReservationTest {

    static String id = "130681200104296464";
    static Passenger passenger = new Passenger();

    static Flight flight1 = new Flight();

    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Reservation reservation1 = new Reservation();

    /**
     * Before all test initiation of creating new data and add to Csv
     */
    @BeforeAll
    public static void init() {
        String bookNumber1 = "1511111111111111111";

        passenger.setPassengerId(id);
        passenger.setFirstName("Test");
        passenger.setLastName("test");
        passenger.setAge(2);
        passenger.setTelephone("11111111111");

        Csv.addCsv(passenger, Csv.PASSENGER_CSV_PATH, true);

        flight1.setFlightId("MH1111");
        flight1.setDeparture("Beijing");
        flight1.setDestination("Hainan");
        flight1.setBoardingGate("C08");
        flight1.setBoardingTime(LocalDateTime.of(2022, 9, 11, 7, 5));
        flight1.setDepartureTime(LocalDateTime.of(2022, 9, 11, 9, 55));
        flight1.setArrivalTime(LocalDateTime.of(2022, 9, 11, 12, 55));

        Csv.addCsv(flight1, Csv.FLIGHT_CSV_PATH, false);

        reservation1.setReservationId(bookNumber1);
        reservation1.setPassenger(passenger);
        reservation1.setFlight(flight1);
        reservation1.setSeatLevel(Seat.FIRST_CLASS);
        reservation1.setMealsAvailable(true);
        reservation1.setHandBaggageNum(0);
        reservation1.setCheckedBaggageNum(1);
        reservations.add(reservation1);

        Csv.addCsv(reservation1, Csv.RESERVATION_CSV_PATH, false);
    }

    /**
     * After all tests deleting all new data from Csv
     */
    @AfterAll
    public static void clear() {
        Csv.deleteCsv(passenger, Csv.PASSENGER_CSV_PATH, true);
        Csv.deleteCsv(flight1, Csv.FLIGHT_CSV_PATH, true);
        Csv.deleteCsv(reservation1, Csv.RESERVATION_CSV_PATH, true);
    }

    /**
     * test lookupReservations by id function
     */
    @Test
    public void lookUpReservationsTest() {
        GetReservation getReservation = new GetReservationImpl();
        assertEquals(reservations, getReservation.lookupReservations(id));
    }
}
