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
    public void csvTest() {
        passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("Test");
        passenger.setLastName("Jun");
        passenger.setTelephone("13104368848");
        passenger.setAge(22);
        String filePath1 = "src/main/resources/com/app/flight/data/csv/Passenger.csv";
        Csv.updateCsv(passenger, filePath1);

        flight = new Flight();
        flight.setFlightId("MH1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Shanghai");
        flight.setBoardingGate("C10");
        flight.setBoardingTime(LocalDateTime.of(2022, 5, 25, 11, 25));
        flight.setDepartureTime(LocalDateTime.of(2022, 5, 25, 11, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 5, 25, 14, 55));

        String filePath2 = "src/main/resources/com/app/flight/data/csv/Flight.csv";
        Csv.addCsv(flight, filePath2, true);

        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        String id = snowflake.nextIdStr();
        reservation = new Reservation();
        reservation.setReservationId(id);
        reservation.setPassenger(passenger);
        reservation.setCheckedBaggageNum(0);
        reservation.setHandBaggageNum(1);
        reservation.setMealsAvailable(true);
        reservation.setSeatLevel(Reservation.seatClass.ECONOMY_CLASS);
        reservation.setFlight(flight);

        String filePath3 = "src/main/resources/com/app/flight/data/csv/Reservation.csv";
        Csv.addCsv(reservation, filePath3, true);
        //Csv.deleteCsv(reservation, filePath, false);
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
