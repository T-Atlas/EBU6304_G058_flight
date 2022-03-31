import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Admin;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.util.JsonToCsv;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HelloTest {
    @Test
    public static void main(String[] args) throws IOException {

        FileWriter pJson = new FileWriter("src/main/resources/com/app/flight/data/json/Passenger.json");
        PrintWriter out = new PrintWriter(pJson);

        ArrayList<Passenger> users = new ArrayList<>();
        Passenger passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("Jun");
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

        String passengerJson = JSON.toJSONString(users, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        out.write(passengerJson);
        pJson.close();
        out.close();
        if (JsonToCsv.addEntityToCsv(passenger2, "src/main/resources/com/app/flight/data/csv/Passenger.csv")) {
            System.out.println("添加csv成功");
        }

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

        String flightJson = JSON.toJSONString(flights, SerializerFeature.PrettyFormat);
        out2.write(flightJson);
        pJson.close();
        out2.close();


        OutputStream rJson = new FileOutputStream("src/main/resources/com/app/flight/data/json/Reservation.json");
        PrintWriter out3 = new PrintWriter(rJson);

        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();

        reservation.setReservationId("001");
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setSeatLevel("High");
        reservation.setMealsAvailable(true);
        reservation.setHandBaggageNum(2);
        reservation.setCheckedBaggageNum(1);
        reservations.add(reservation);

        String reservationJson = JSON.toJSONString(reservations, SerializerFeature.PrettyFormat);
        out3.write(reservationJson);
        pJson.close();
        out3.close();

        Admin admin = new Admin();
        admin.setId(111);
        admin.setTelephone("111");
        System.out.println(admin);

    }
}
