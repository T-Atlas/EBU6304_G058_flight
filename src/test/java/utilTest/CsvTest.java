package utilTest;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.app.flight.entity.*;
import com.app.flight.util.Csv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.app.flight.util.Csv.addCsv;
import static com.app.flight.util.Csv.deleteCsv;

public class CsvTest {
    Passenger passenger;
    Flight flight;
    Reservation reservation;
    Food food;

    /**
     * Test for csv
     */
    @Test
    @DisplayName("sd")
    public void csvTest() {
        passenger = new Passenger();
        passenger.setPassengerId("130621200508296868");
        passenger.setFirstName("p1");
        passenger.setLastName("test");
        passenger.setTelephone("13104368848");
        passenger.setAge(21);
        assert Csv.addCsv(passenger, Csv.PASSENGER_CSV_PATH, true) : "Add passenger csv failed";
        passenger.setFirstName("p2");
        assert Csv.updateCsv(passenger, Csv.PASSENGER_CSV_PATH) : "Update passenger csv failed";
        Csv.readCsv(Csv.PASSENGER_CSV_PATH);
        assert deleteCsv(passenger, Csv.PASSENGER_CSV_PATH, true) : "Delete passenger csv failed";

        flight = new Flight();
        flight.setFlightId("MH1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Shanghai");
        flight.setBoardingGate("C10");
        flight.setBoardingTime(LocalDateTime.of(2022, 5, 25, 11, 25));
        flight.setDepartureTime(LocalDateTime.of(2022, 5, 25, 11, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 5, 25, 14, 55));
        assert Csv.addCsv(flight, Csv.FLIGHT_CSV_PATH, false) : "Add flight csv failed";
        flight.setBoardingGate("A10");
        assert Csv.updateCsv(flight, Csv.FLIGHT_CSV_PATH) : "Update flight csv failed";
        Csv.readCsv(Csv.FLIGHT_CSV_PATH);
        assert deleteCsv(flight, Csv.FLIGHT_CSV_PATH, true) : "Delete flight csv failed";

        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        String id = snowflake.nextIdStr();
        reservation = new Reservation();
        reservation.setReservationId(id);
        reservation.setPassenger(passenger);
        reservation.setCheckedBaggageNum(0);
        reservation.setHandBaggageNum(1);
        reservation.setMealsAvailable(true);
        reservation.setSeatLevel(Seat.FIRST_CLASS);
        reservation.setFlight(flight);
        assert Csv.addCsv(reservation, Csv.RESERVATION_CSV_PATH, true) : "Add reservation csv failed";
        reservation.setSeatLevel(Seat.BUSINESS_CLASS);
        assert Csv.updateCsv(reservation, Csv.RESERVATION_CSV_PATH) : "Update reservation csv failed";
        Csv.readCsv(Csv.RESERVATION_CSV_PATH);
        assert deleteCsv(reservation, Csv.RESERVATION_CSV_PATH, true) : "Delete reservation csv failed";

        food = new Food();
        food.setFoodName(Food.foodType.STANDARD);
        food.setFoodPrice(40.0);
        assert Csv.updateCsv(food, Csv.FOOD_CSV_PATH) : "Update food csv failed";
        Csv.readCsv(Csv.FOOD_CSV_PATH);
        assert deleteCsv(food, Csv.FOOD_CSV_PATH, true) : "Delete food csv failed";
        food.setFoodPrice(30.0);
        assert addCsv(food, Csv.FOOD_CSV_PATH, false) : "Add food csv failed";
    }
}
