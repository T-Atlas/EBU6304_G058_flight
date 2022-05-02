package serviceTest;

import com.app.flight.service.GetSeatMap;
import com.app.flight.service.impl.GetSeatMapImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetSeatMapTest {
    @Test
    public void getSeatMapTest() {
        GetSeatMap getSeatMap = new GetSeatMapImpl();
        Map<Integer, Map<String, Boolean>> mu1122 = getSeatMap.lookupSeatMap("MU1122");
        System.out.println(mu1122);
    }
}
