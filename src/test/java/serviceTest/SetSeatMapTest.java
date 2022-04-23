package serviceTest;

import com.app.flight.service.SetSeatMap;
import com.app.flight.service.impl.SetSeatMapImpl;
import org.junit.jupiter.api.Test;

public class SetSeatMapTest {
    @Test
    public void updateSeatMapTest() {
        SetSeatMap setSeatMap = new SetSeatMapImpl();
        setSeatMap.updateSeatMap("MU1122", "A", 1);
    }
}
