import com.app.flight.entity.Passenger;
import org.junit.jupiter.api.Test;

import static com.app.flight.util.Validator.idValidator;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author JiaBoran
 * @version 1.0
 * @date 2022.5.5
 */
public class IdValidatorTest {

    @Test
    public void testId() {
        Passenger passenger1 = new Passenger();
        passenger1.setPassengerId("210122196110070924");
        assertTrue(idValidator(passenger1.getPassengerId()));
    }
}
