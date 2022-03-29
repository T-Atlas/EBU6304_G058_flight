import com.alibaba.fastjson.JSON;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class HelloTest {
    @Test
    public static void main(String[] args) throws IOException {

        Passenger passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("Junhong");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);

        Flight flight = new Flight();
        flight.setBoardingTime(LocalDateTime.now());
        String s = JSON.toJSONString(flight);
        System.out.println(s);

        FileWriter fw = new FileWriter("src/main/resources/com/app/flight/data/json/Flight.json");
        PrintWriter out = new PrintWriter(fw);
        out.write(s);
        out.println();
        fw.close();
        out.close();


    }
}
