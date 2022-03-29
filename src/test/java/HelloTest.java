import cn.hutool.core.date.LocalDateTimeUtil;
import com.app.flight.entity.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import com.app.flight.entity.Passenger;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class HelloTest {
    @Test
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/com/app/flight/data/json/Passenger.json");

        Passenger passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("Junhong");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, passenger);
        Passenger passenger1 = mapper.readValue(file, Passenger.class);
        System.out.println(passenger1);


    }
}
