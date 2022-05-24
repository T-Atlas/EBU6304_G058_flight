package serviceTest;

import com.app.flight.entity.Flight;
import com.app.flight.service.GetFlight;
import com.app.flight.service.impl.GetFlightImpl;
import com.app.flight.util.Csv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaBoran
 * @version 2.0
 * Test class for testing getting flight
 */
public class GetFlightTest {
    static String flightId = "MH8633";
    static Flight flight = new Flight();

    /**
     * Before all tests initiation of creating new data and add to Csv
     */
    @BeforeAll
    public static void init() {
        flight.setFlightId(flightId);
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("B08");
        flight.setBoardingTime(LocalDateTime.of(2022, 9, 11, 7, 5));
        flight.setDepartureTime(LocalDateTime.of(2022, 9, 11, 9, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 9, 11, 12, 55));
        Csv.addCsv(flight, Csv.FLIGHT_CSV_PATH, false);
    }

    /**
     * After tests deleting all the new data from Csv
     */
    @AfterAll
    public static void clear() {
        Csv.deleteCsv(flight, Csv.FLIGHT_CSV_PATH, true);
    }

    /**
     * test for lookupFlight by flightId function
     */
    @Test
    public void lookupFlightTest() {
        GetFlight getFlight = new GetFlightImpl();
        assertEquals(flight, getFlight.lookupFlight(flightId));
    }
}
