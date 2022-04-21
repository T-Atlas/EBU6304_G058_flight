import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Food;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.util.Csv;
import com.app.flight.util.Seat;
import org.junit.Test;

import java.time.LocalDateTime;

public class CsvTest {
    Passenger passenger;
    Flight flight;
    Flight flight1;
    Reservation reservation;

    @Test
    public void passengerCsvTest() {
        passenger = new Passenger();
        passenger.setPassengerId("220802200005217774");
        passenger.setFirstName("Test");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        String filePath = "src/main/resources/com/app/flight/data/csv/Passenger.csv";
        Csv.updateCsv(passenger, filePath);
    }

    @Test
    public void flightCsvTest() {
        flight = new Flight();
        flight.setFlightId("MU1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("A10");
        flight.setBoardingTime(LocalDateTime.of(2022, 1, 23, 11, 25));
        flight.setDepartureTime(LocalDateTime.of(2022, 1, 23, 11, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 1, 23, 16, 55));

        flight1 = new Flight();
        flight1.setFlightId("MU1122");
        flight1.setDeparture("Shanghai");
        flight1.setDestination("Beijing");
        flight1.setBoardingGate("D20");
        flight1.setBoardingTime(LocalDateTime.of(2022, 4, 11, 12, 25));
        flight1.setDepartureTime(LocalDateTime.of(2022, 4, 11, 12, 55));
        flight1.setArrivalTime(LocalDateTime.of(2022, 4, 11, 14, 55));

        String filePath2 = "src/main/resources/com/app/flight/data/csv/Flight.csv";
        Csv.addCsv(flight1, filePath2, true);
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
        Csv.addCsv(food1, filePath, true);
        Csv.addCsv(food2, filePath, true);
        Csv.addCsv(food3, filePath, true);
    }

    @Test
    public void seatMapTest() {
        Seat.generateSeatMap("MU1122", 20);
    }
}
