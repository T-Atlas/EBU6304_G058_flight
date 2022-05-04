package serviceTest;

import com.app.flight.service.SetSeatMap;
import com.app.flight.service.impl.SeatMapImpl;
import org.junit.jupiter.api.Test;

public class SetSeatMapTest {
    @Test
    public void updateSeatMapTest() {
        SetSeatMap setSeatMap = new SeatMapImpl();
        setSeatMap.updateSeatMap("MU1122", "A", 1);
    }
}
