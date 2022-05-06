import com.app.flight.entity.Flight;
import org.junit.jupiter.api.Test;

import static com.app.flight.util.Validator.flightIdValidator;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author JiaBoran
 * @version 1.0
 * @date 2022.5.5
 */
public class FlightIdValidatorTest {
    Flight flight;

    @Test
    public void testFlightId() {
        flight = new Flight();
        flight.setFlightId("MU1121");
        assertTrue(flightIdValidator(flight.getFlightId()));
    }
}
