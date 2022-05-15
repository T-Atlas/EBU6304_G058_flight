package serviceTest;

import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.service.impl.GetBoardingPassImpl;
import com.app.flight.service.impl.SeatMapImpl;
import com.app.flight.util.Json;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBoardingPassTest {
    static BoardingPass boardingPass = new BoardingPass();

    @BeforeAll
    public static void initEnvironment() {
        String id = "123456";

        Passenger passenger = new Passenger();
        passenger.setPassengerId(id);
        passenger.setFirstName("Test");
        passenger.setLastName("Jun");
        passenger.setAge(22);
        passenger.setTelephone("13104368848");

        Flight flight = new Flight();
        flight.setFlightId("MH1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Shanghai");
        flight.setBoardingGate("D05");
        flight.setBoardingTime(LocalDateTime.of(2022, 10, 11, 7, 5));
        flight.setDepartureTime(LocalDateTime.of(2022, 10, 11, 9, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 10, 11, 12, 55));

        Food food = new Food();
        food.setFoodName(Food.foodType.HALAL);
        food.setFoodPrice(50.0);

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

    @Test
    public void lookupBoardingPassTest() {

        GetBoardingPass getBoardingPass = new GetBoardingPassImpl();
        assertEquals(boardingPass, getBoardingPass.lookupBoardingPass());
    }
}


