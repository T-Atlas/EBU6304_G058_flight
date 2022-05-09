package serviceTest;

import com.app.flight.service.GetSeatMap;
import com.app.flight.service.impl.SeatMapImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetSeatMapTestUtil {
    @Test
    public void getSeatMapTest() {
        GetSeatMap getSeatMap = new SeatMapImpl();
        Map<Integer, Map<String, Boolean>> mu1122 = getSeatMap.lookupSeatMap("MU1122");
        System.out.println(mu1122);
    }
}
