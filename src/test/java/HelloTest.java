
import com.alibaba.fastjson.JSON;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Reservation;
import org.junit.jupiter.api.Test;
import com.app.flight.entity.Passenger;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class HelloTest {
    @Test
    public static void main(String[] args) throws IOException {

        FileWriter pJson = new FileWriter("src/main/resources/com/app/flight/data/json/Passenger.json");
        PrintWriter out = new PrintWriter(pJson);


        ArrayList<Passenger> users = new ArrayList<>();
        Passenger passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("Junhong");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        users.add(passenger);
        Passenger passenger2 = new Passenger();
        passenger2.setPassengerId("220802200005216666");
        passenger2.setFirstName("Bo");
        passenger2.setLastName("Song");
        passenger2.setTelephone("13104365555");
        passenger2.setAge(18);
        users.add(passenger2);

        String passengerJson = JSON.toJSONString(users);
        out.write(passengerJson);
        pJson.close();
        out.close();


        //Flight.json
        OutputStream fJson = new FileOutputStream("src/main/resources/com/app/flight/data/json/Flight.json");
        PrintWriter out2 = new PrintWriter(fJson);

        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();
        flight.setFlightId("MU1234");
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("D11");
        flight.setBoardingTime(LocalDateTime.now());
        flight.setDepartureTime(LocalDateTime.of(2022, 7, 11, 17, 22, 50));
        flight.setArrivalTime(LocalDateTime.of(2022, 7, 11, 20, 22, 50));
        flights.add(flight);

        String flightJson = JSON.toJSONString(flights);
        out2.write(flightJson);
        pJson.close();
        out2.close();


        OutputStream rJson = new FileOutputStream("src/main/resources/com/app/flight/data/json/Reservation.json");
        PrintWriter out3 = new PrintWriter(rJson);

        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();

        reservation.setReservationId("001");
        reservation.setPassenger(passenger);
        //reservation.setFlight(flight);
        reservation.setSeatLevel("High");
        reservation.setMealsAvailable(true);
        reservation.setHandBaggageNum(2);
        reservation.setCheckedBaggageNum(1);
        reservations.add(reservation);

        String reservationJson = JSON.toJSONString(reservations);
        out3.write(reservationJson);
        pJson.close();
        out3.close();

    }
}
