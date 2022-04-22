package serviceTest;

import com.app.flight.service.impl.SetSeatMapImpl;
import org.junit.jupiter.api.Test;

public class SetSeatMapTest {
    @Test
    public void updateSeatMapTest() {
        SetSeatMapImpl setSeatMap = new SetSeatMapImpl();
        setSeatMap.updateSeatMap("MU1122", "A", 1);
    }
}
