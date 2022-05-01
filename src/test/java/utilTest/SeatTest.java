package utilTest;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.app.flight.util.Seat;
import org.junit.jupiter.api.Test;


public class SeatTest {
    @Test
    public void generateSeatMapTest() {
        Seat.generateSeatMap("MH8633", 20);
    }

    @Test
    public void jsonPathTest() {
        String seatString = "{\n" +
                "\t\"flightId\":\"MH8633\",\n" +
                "\t\"column\":\"C\",\n" +
                "\t\"row\":\"19\"\n" +
                "}";
        JSONPath rowPath = JSONPath.of("$.row");
        JSONPath colPath = JSONPath.of("$.column");
        JSONReader parser = JSONReader.of(seatString);
        String row = (String) rowPath.extract(parser);
        String col = (String) colPath.extract(parser);
        System.out.println(row);
        System.out.println(col);
    }
}
