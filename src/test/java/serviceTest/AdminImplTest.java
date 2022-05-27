package serviceTest;

import com.app.flight.entity.*;
import com.app.flight.service.impl.AdminImpl;
import com.app.flight.util.Csv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminImplTest {
    static String id = "130681200104296464";
    static String adminId = "2019213500";
    static String passWord = "190894000";
    static String flightId = "MH2222";
    static String bookNumber = "6666666666666666666";
    static Passenger passenger = new Passenger();
    static Admin admin = new Admin();
    static Flight flight = new Flight();
    static Food food = new Food();
    static BoardingPass boardingPass = new BoardingPass();
    static Reservation reservation = new Reservation();

    @BeforeAll
    public static void init() {
        passenger.setPassengerId(id);
        passenger.setFirstName("Test");
        passenger.setLastName("Jia");
        passenger.setAge(22);
        passenger.setTelephone("13104368848");
        Csv.addCsv(passenger, Csv.PASSENGER_CSV_PATH, true);

        admin.setId(adminId);
        admin.setPassword(passWord);
        admin.setName("Jia");
        admin.setTelephone("15611361588");
        Csv.addCsv(admin, Csv.ADMIN_CSV_PATH, true);

        flight.setFlightId(flightId);
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("B08");
        flight.setBoardingTime(LocalDateTime.of(2022, 9, 11, 7, 5));
        flight.setDepartureTime(LocalDateTime.of(2022, 9, 11, 9, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 9, 11, 12, 55));
        Csv.addCsv(flight, Csv.FLIGHT_CSV_PATH, false);

        food.setFoodName(Food.foodType.STANDARD);
        food.setFoodPrice(30.0);
        Csv.addCsv(food, Csv.FOOD_CSV_PATH, false);

        boardingPass.setPassenger(passenger);
        boardingPass.setFlight(flight);
        boardingPass.setSeatNo("2D");
        boardingPass.setFood(food);
        Csv.addCsv(boardingPass, Csv.BOARDING_PASS_CSV_PATH, true);

        reservation.setReservationId(bookNumber);
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setSeatLevel(Seat.FIRST_CLASS);
        reservation.setMealsAvailable(true);
        reservation.setHandBaggageNum(0);
        reservation.setCheckedBaggageNum(1);
        reservation.setChecked(true);
        Csv.addCsv(reservation, Csv.RESERVATION_CSV_PATH, false);
    }

    @AfterAll
    public static void clear() {
        Csv.deleteCsv(passenger, Csv.PASSENGER_CSV_PATH, true);
        Csv.deleteCsv(admin, Csv.ADMIN_CSV_PATH, true);
        Csv.deleteCsv(flight, Csv.FLIGHT_CSV_PATH, true);
        Csv.deleteCsv(food, Csv.FOOD_CSV_PATH, true);
        Csv.deleteCsv(boardingPass, Csv.BOARDING_PASS_CSV_PATH, true);
        Csv.deleteCsv(reservation, Csv.RESERVATION_CSV_PATH, true);
    }

    @Test
    public void getPasswordTest() {
        AdminImpl adminImpl = new AdminImpl();
        assertEquals(passWord, adminImpl.getPassword(adminId));
    }

    @Test
    public void getNameTest() {
        AdminImpl adminImpl = new AdminImpl();
        assertEquals(admin.getName(), adminImpl.getName(adminId));
    }
}
