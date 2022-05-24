package serviceTest;


import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.service.impl.GetBoardingPassImpl;
import com.app.flight.util.Json;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.alibaba.fastjson2.JSON.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBoardingPassTest {
    static String id = "123456";
    static BoardingPass boardingPass;
    static Passenger passenger;
    static Flight flight;
    static Food food;

    @BeforeAll
    public static void init() {
        boardingPass = new BoardingPass();
        String passengerString = Json.extractJsonData(Json.PASSENGER_JSON_PATH);
        passenger = parseObject(passengerString, Passenger.class);
        boardingPass.setPassenger(passenger);

        String flightString = Json.extractJsonData(Json.FLIGHT_JSON_PATH);
        flight = parseObject(flightString, Flight.class);
        boardingPass.setFlight(flight);

        String foodString = Json.extractJsonData(Json.FOOD_JSON_PATH);
        food = parseObject(foodString, Food.class);
        boardingPass.setFood(food);

        String seatString = Json.extractJsonData(Json.SEAT_JSON_PATH);
        JSONPath rowPath = JSONPath.of("$.row");
        JSONPath colPath = JSONPath.of("$.column");
        String row = (String) rowPath.extract(JSONReader.of(seatString));
        String col = (String) colPath.extract(JSONReader.of(seatString));
        boardingPass.setSeatNo(row + col);
    }

    @Test
    public void lookupBoardingPassTest() {
        GetBoardingPass getBoardingPass = new GetBoardingPassImpl();
        assertEquals(boardingPass, getBoardingPass.lookupBoardingPass());
    }
}


