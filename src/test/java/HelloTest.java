import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import com.app.flight.entity.Passenger;

import java.io.*;
import java.util.ArrayList;

public class HelloTest {
    @Test
    public static void main(String[] args) throws IOException {
        OutputStream out = new FileOutputStream("src/main/resources/com/app/flight/data/json/Passenger.json");
        PrintWriter pw = new PrintWriter(out);

        ArrayList<Passenger> users = new ArrayList<Passenger>();
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
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(pw, users);
    }
}
