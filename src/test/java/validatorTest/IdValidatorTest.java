package validatorTest;

import com.app.flight.entity.Passenger;
import org.junit.jupiter.api.Test;

import static cn.hutool.core.util.IdcardUtil.isValidCard;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author JiaBoran
 * @version 1.0
 * Test class for IdValidator
 */
public class IdValidatorTest {

    /**
     * test for id validator
     */
    @Test
    public void testId() {
        Passenger passenger1 = new Passenger();
        passenger1.setPassengerId("210122196110070924");
        assertTrue(isValidCard(passenger1.getPassengerId()));

        Passenger passenger2 = new Passenger();
        passenger2.setPassengerId("130681200104296465");
        assertFalse(isValidCard(passenger2.getPassengerId()));
    }
}
