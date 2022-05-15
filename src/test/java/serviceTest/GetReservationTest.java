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

public class GetReservationTest {

    static String id = "123456789";
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Reservation reservation1 = new Reservation();
    static Reservation reservation2 = new Reservation();

    @BeforeAll
    public static void init() {
        String bookNumber1 = "1517539047050973184";
        String bookNumber2 = "1517540042405449728";

        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);
        passenger.setFirstName("Test");
        passenger.setLastName("Jun");
        passenger.setAge(22);
        passenger.setTelephone("13104368848");

        Flight flight1 = new Flight();
        flight1.setFlightId("MH8633");
        flight1.setDeparture("Beijing");
        flight1.setDestination("Hainan");
        flight1.setBoardingGate("B08");
        flight1.setBoardingTime(LocalDateTime.of(2022, 9, 11, 7, 5));
        flight1.setDepartureTime(LocalDateTime.of(2022, 9, 11, 9, 55));
        flight1.setArrivalTime(LocalDateTime.of(2022, 9, 11, 12, 55));

        Flight flight2 = new Flight();
        flight2.setFlightId("MH1234");
        flight2.setDeparture("Beijing");
        flight2.setDestination("Shanghai");
        flight2.setBoardingGate("D05");
        flight2.setBoardingTime(LocalDateTime.of(2022, 10, 11, 7, 5));
        flight2.setDepartureTime(LocalDateTime.of(2022, 10, 11, 9, 55));
        flight2.setArrivalTime(LocalDateTime.of(2022, 10, 11, 12, 55));

        reservation1.setReservationId(bookNumber1);
        reservation1.setPassenger(passenger);
        reservation1.setFlight(flight1);
        reservation1.setSeatLevel(Seat.FIRST_CLASS);
        reservation1.setMealsAvailable(true);
        reservation1.setHandBaggageNum(0);
        reservation1.setCheckedBaggageNum(1);
        reservations.add(reservation1);

        reservation2.setReservationId(bookNumber2);
        reservation2.setPassenger(passenger);
        reservation2.setFlight(flight2);
        reservation2.setSeatLevel(Seat.ECONOMY_CLASS);
        reservation2.setMealsAvailable(true);
        reservation2.setHandBaggageNum(1);
        reservation2.setCheckedBaggageNum(0);
        reservations.add(reservation2);

        Csv.addCsv(reservation1, Csv.RESERVATION_CSV_PATH, false);
        Csv.addCsv(reservation2, Csv.RESERVATION_CSV_PATH, false);

    }

    @AfterAll
    public static void clear() {
        Csv.deleteCsv(reservation1, Csv.RESERVATION_CSV_PATH, true);
        Csv.deleteCsv(reservation2, Csv.RESERVATION_CSV_PATH, true);
    }

    @Test
    public void lookUpReservationsTest() {
        GetReservation getReservation = new GetReservationImpl();

        //assertEquals(reservations, getReservation.lookupReservations(id));
    }
}
