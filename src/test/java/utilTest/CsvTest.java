package utilTest;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.util.Csv;
import org.junit.Test;

import java.time.LocalDateTime;

public class CsvTest {
    Passenger passenger;
    Flight flight;
    Reservation reservation;

    @Test
    public void passengerCsvTest() {
        passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("Test");
        passenger.setLastName("Jun");
        passenger.setTelephone("13104368848");
        passenger.setAge(22);
        String filePath = "src/main/resources/com/app/flight/data/csv/Passenger.csv";
        Csv.updateCsv(passenger, filePath);
    }

    @Test
    public void flightCsvTest() {
        flight = new Flight();
        flight.setFlightId("MU1122");
        flight.setDeparture("Shanghai");
        flight.setDestination("Beijing");
        flight.setBoardingGate("D20");
        flight.setBoardingTime(LocalDateTime.of(2022, 4, 11, 12, 25));
        flight.setDepartureTime(LocalDateTime.of(2022, 4, 11, 12, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 4, 11, 14, 55));

        String filePath = "src/main/resources/com/app/flight/data/csv/Flight.csv";
        Csv.addCsv(flight, filePath, true);
    }

    @Test
    public void reservationCsvTest() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        String id = snowflake.nextIdStr();
        reservation = new Reservation();
        reservation.setReservationId(id);
        reservation.setPassenger(passenger);
        reservation.setCheckedBaggageNum(2);
        reservation.setHandBaggageNum(1);
        reservation.setMealsAvailable(true);
        reservation.setSeatLevel(Reservation.seatClass.BUSINESS_CLASS);
        reservation.setFlight(flight);

        String filePath = "src/main/resources/com/app/flight/data/csv/Reservation.csv";
        Csv.addCsv(reservation, filePath, true);
        Csv.deleteCsv(reservation, filePath, false);
    }

    @Test
    public void foodCsvTest() {
        Food food1 = new Food();
        food1.setFoodName(Food.foodType.STANDARD);
        food1.setFoodPrice(Double.parseDouble("30"));

        Food food2 = new Food();
        food2.setFoodName(Food.foodType.HALAL);
        food2.setFoodPrice(Double.parseDouble("50"));

        Food food3 = new Food();
        food3.setFoodName(Food.foodType.VEGETARIAN);
        food3.setFoodPrice(Double.parseDouble("20"));

        String filePath = "src/main/resources/com/app/flight/data/csv/Food.csv";
        Csv.updateCsv(food1, filePath);
        Csv.updateCsv(food2, filePath);
        Csv.updateCsv(food3, filePath);
    }
}
