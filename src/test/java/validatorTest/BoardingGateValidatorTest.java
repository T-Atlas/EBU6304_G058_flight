package validatorTest;

import com.app.flight.entity.Flight;
import org.junit.jupiter.api.Test;

import static com.app.flight.util.Validator.boardingGateValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author JiaBoran
 * @version 1.0
 * Test class for BoardingGateValidator
 */
public class BoardingGateValidatorTest {

    /**
     * test for BoardingGate using partition test
     */
    @Test
    public void testBoardingGate() {
        Flight flight1 = new Flight();
        flight1.setBoardingGate("A03");// correct version
        assertTrue(boardingGateValidator(flight1.getBoardingGate()));

        Flight flight2 = new Flight();
        flight2.setBoardingGate("s02");// first digit is not A and B and C and D
        assertFalse(boardingGateValidator(flight2.getBoardingGate()));

        Flight flight3 = new Flight();
        flight3.setBoardingGate("Aa0");// last two digits not both digits
        assertFalse(boardingGateValidator(flight3.getBoardingGate()));

        Flight flight4 = new Flight();
        flight4.setBoardingGate("A001");// more than 3 digits
        assertFalse(boardingGateValidator(flight4.getBoardingGate()));

        Flight flight5 = new Flight();
        flight5.setBoardingGate("A0");// less than 3 digits
        assertFalse(boardingGateValidator(flight5.getBoardingGate()));
    }
}
