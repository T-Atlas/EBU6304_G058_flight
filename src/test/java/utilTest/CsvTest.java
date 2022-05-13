package utilTest;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.app.flight.entity.*;
import com.app.flight.util.Csv;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
    public void CsvTest() {
        passenger = new Passenger();
        passenger.setPassengerId("210122196110070924");
        passenger.setFirstName("p1");
        passenger.setLastName("test");
        passenger.setTelephone("13104368848");
        passenger.setAge(21);
        //Test add csv
        assert Csv.addCsv(passenger, Csv.PASSENGER_CSV_PATH, true) : "Add passenger csv failed";
        //Test update csv
        passenger.setFirstName("p2");
        assert Csv.updateCsv(passenger, Csv.PASSENGER_CSV_PATH) : "Update passenger csv failed";
        //Test read csv
        Csv.readCsv(Csv.PASSENGER_CSV_PATH);
        //Test delete csv
        assert deleteCsv(passenger, Csv.PASSENGER_CSV_PATH, true) : "Delete passenger csv failed";

        flight = new Flight();
        flight.setFlightId("MH1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Shanghai");
        flight.setBoardingGate("C10");
        flight.setBoardingTime(LocalDateTime.of(2022, 5, 25, 11, 25));
        flight.setDepartureTime(LocalDateTime.of(2022, 5, 25, 11, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 5, 25, 14, 55));
        //Test add csv
        assert Csv.addCsv(flight, Csv.FLIGHT_CSV_PATH, false) : "Add flight csv failed";
        //Test update csv
        flight.setBoardingGate("A10");
        assert Csv.updateCsv(flight, Csv.FLIGHT_CSV_PATH) : "Update flight csv failed";
        //Test read csv
        Csv.readCsv(Csv.FLIGHT_CSV_PATH);
        //Test delete csv
        assert deleteCsv(flight, Csv.FLIGHT_CSV_PATH, false) : "Delete flight csv failed";

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
        //Test add csv
        //assert Csv.addCsv(reservation, Csv.RESERVATION_CSV_PATH, true):"Add reservation csv failed";
        //Test update csv
        reservation.setSeatLevel(Seat.BUSINESS_CLASS);
        //assert Csv.updateCsv(reservation, Csv.RESERVATION_CSV_PATH):"Update reservation csv failed";
        //Test read csv
        //Csv.readCsv(Csv.RESERVATION_CSV_PATH);
        //Test delete csv
        //assert deleteCsv(reservation, Csv.RESERVATION_CSV_PATH, true):"Delete reservation csv failed";

        food = new Food();
        food.setFoodName(Food.foodType.STANDARD);
        food.setFoodPrice(30.0);
        //Test add csv
        //assert Csv.addCsv(food,Csv.FOOD_CSV_PATH,false):"Add food csv failed";
        //Test update csv
        food.setFoodPrice(10.0);
        //assert Csv.updateCsv(food,Csv.FOOD_CSV_PATH):"Update food csv failed";
        //Test read csv
        //Csv.readCsv(Csv.FOOD_CSV_PATH);
        //Test delete csv
        //assert deleteCsv(food,Csv.FOOD_CSV_PATH,false):"Delete food csv failed";
    }
}
