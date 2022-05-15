package serviceTest;

import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.service.impl.GetBoardingPassImpl;
import com.app.flight.service.impl.SeatMapImpl;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBoardingPassTest {
    static String id = "123456";
    static BoardingPass boardingPass = new BoardingPass();
    static Passenger passenger = new Passenger();
    static Flight flight = new Flight();
    static Food food = new Food();

    @BeforeAll
    public static void init() {
        passenger.setPassengerId(id);
        passenger.setFirstName("Test");
        passenger.setLastName("Jun");
        passenger.setAge(22);
        passenger.setTelephone("13104368848");
        Csv.addCsv(passenger, Csv.PASSENGER_CSV_PATH, true);

        flight.setFlightId("MH1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Shanghai");
        flight.setBoardingGate("D05");
        flight.setBoardingTime(LocalDateTime.of(2022, 10, 11, 7, 5));
        flight.setDepartureTime(LocalDateTime.of(2022, 10, 11, 9, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 10, 11, 12, 55));
        Csv.addCsv(flight, Csv.FLIGHT_CSV_PATH, false);

        food.setFoodName(Food.foodType.HALAL);
        food.setFoodPrice(50.0);
        Csv.addCsv(food, Csv.FOOD_CSV_PATH, false);

        SeatMapImpl seatMap = new SeatMapImpl();
        seatMap.updateSeatMap(flight.getFlightId(), "B", 1, 400.0);

        boardingPass.setPassenger(passenger);
        boardingPass.setFlight(flight);
        boardingPass.setSeatNo("1B");
        boardingPass.setFood(food);

        Json.writeJson(Json.FOOD_JSON_PATH, food);
        Json.writeJson(Json.PASSENGER_JSON_PATH, passenger);
        Json.writeJson(Json.FLIGHT_JSON_PATH, flight);
        Json.writeJson(Json.BOARDING_PASS_JSON_PATH, boardingPass);

    }

    @AfterAll
    public static void clear() {
        Csv.deleteCsv(passenger, Csv.PASSENGER_CSV_PATH, true);
        Csv.deleteCsv(flight, Csv.FLIGHT_CSV_PATH, true);
        Csv.deleteCsv(food, Csv.FOOD_CSV_PATH, true);
    }

    @Test
    public void lookupBoardingPassTest() {
        GetBoardingPass getBoardingPass = new GetBoardingPassImpl();
        assertEquals(boardingPass, getBoardingPass.lookupBoardingPass());
    }
}


