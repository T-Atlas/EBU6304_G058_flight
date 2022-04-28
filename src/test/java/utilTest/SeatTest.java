package utilTest;

import com.app.flight.util.Seat;
import org.junit.jupiter.api.Test;


public class SeatTest {
    @Test
    public void generateSeatMapTest() {
        Seat.generateSeatMap("MH8633", 20);
    }
}
