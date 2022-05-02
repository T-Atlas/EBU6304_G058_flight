package dependenciesTest;

import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.util.Json;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Mason
 * @author LianJunhong
 */
public class OrdinalTest {
    Reservation reservation;
    Passenger passenger;
    Flight flight;

    public void OrdinalTestInit() {

        reservation = new Reservation();
        passenger = new Passenger();
        flight = new Flight();

        passenger.setPassengerId("123456");
        passenger.setFirstName("Mason");
        passenger.setLastName("Test");
        passenger.setAge(20);
        passenger.setTelephone("13104368848");

        flight.setFlightId("MU1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("A10");
        flight.setBoardingTime(LocalDateTime.parse("2022-04-29T11:25:00"));
        flight.setDepartureTime(LocalDateTime.parse("2022-04-29T11:55:00"));
        flight.setArrivalTime(LocalDateTime.parse("2022-04-29T16:55:00"));

        reservation.setReservationId("1517539047050973184");
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setMealsAvailable(true);
        reservation.setCheckedBaggageNum(1);
        reservation.setHandBaggageNum(0);
        reservation.setSeatLevel(Reservation.seatClass.FIRST_CLASS);

    }

    @Test
    public void ordinalTest() {

        OrdinalTestInit();
        String reservationStr = Json.toJSONString(reservation);
        System.out.println(reservationStr);
        assertEquals("{\"reservationId\":\"1517539047050973184\",\"passenger\":{\"passengerId\":\"123456\",\"firstName\":\"Mason\",\"lastName\":\"Test\",\"age\":20,\"telephone\":\"13104368848\"},\"flight\":{\"flightId\":\"MU1234\",\"departure\":\"Beijing\",\"destination\":\"Hainan\",\"boardingGate\":\"A10\",\"boardingTime\":\"2022-04-29T11:25:00\",\"departureTime\":\"2022-04-29T11:55:00\",\"arrivalTime\":\"2022-04-29T16:55:00\"},\"seatLevel\":\"FIRST_CLASS\",\"mealsAvailable\":true,\"handBaggageNum\":0,\"checkedBaggageNum\":1}", reservationStr);
    }
}
