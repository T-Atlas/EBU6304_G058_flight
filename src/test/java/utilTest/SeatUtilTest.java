package utilTest;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.app.flight.util.SeatUtil;
import org.junit.jupiter.api.Test;


public class SeatUtilTest {
    @Test
    public void generateSeatMapTest() {
        SeatUtil.generateSeatMap("MH8633", 20);
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
        String row = (String) rowPath.extract(JSONReader.of(seatString));
        String col = (String) colPath.extract(JSONReader.of(seatString));
        System.out.println(row);
        System.out.println(col);
    }
}
