package validatorTest;

import com.app.flight.entity.Flight;
import org.junit.jupiter.api.Test;

import static com.app.flight.util.Validator.flightIdValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author JiaBoran
 * @version 1.0
 * Test class for FlightIdValidator
 */
public class FlightIdValidatorTest {

    /**
     * test for FlightId using partition test
     */
    @Test
    public void testFlightId() {
        Flight flight1 = new Flight();
        flight1.setFlightId("MU1121");//correct version: First two big case letter and four digits
        assertTrue(flightIdValidator(flight1.getFlightId()));

        Flight flight2 = new Flight();
        flight2.setFlightId("Aa1236");// First two digits contain not all uppercase
        assertFalse(flightIdValidator(flight2.getFlightId()));

        Flight flight3 = new Flight();
        flight3.setFlightId("AS12aa");//last four digits contain not all digits
        assertFalse(flightIdValidator(flight3.getFlightId()));

        Flight flight4 = new Flight();
        flight4.setFlightId("AW111");//less than 6 digits
        assertFalse(flightIdValidator(flight4.getFlightId()));

        Flight flight5 = new Flight();
        flight5.setFlightId("AS12365");// more than 6 digits
        assertFalse(flightIdValidator(flight5.getFlightId()));
    }
}
