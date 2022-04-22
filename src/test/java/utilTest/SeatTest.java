package utilTest;

import com.app.flight.util.Seat;
import org.junit.Test;

public class SeatTest {
    @Test
    public void generateSeatMapTest() {
        Seat.generateSeatMap("MU1122", 20);
    }
}
