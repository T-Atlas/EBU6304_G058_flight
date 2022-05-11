package validatorTest;

import com.app.flight.entity.Reservation;
import org.junit.jupiter.api.Test;

import static com.app.flight.util.Validator.reservationIdValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author JiaBoran
 * @version 1.0
 * @date 2022.5.5
 */
public class ReservationIdValidatorTest {

    @Test
    public void testReservationId() {
        Reservation reservation1 = new Reservation();
        reservation1.setReservationId("1234567891234567891");//correct version 19 digits
        assertTrue(reservationIdValidator(reservation1.getReservationId()));

        Reservation reservation2 = new Reservation();
        reservation2.setReservationId("123456789123456789");// less than 19 digits
        assertFalse(reservationIdValidator(reservation2.getReservationId()));

        Reservation reservation3 = new Reservation();
        reservation3.setReservationId("123456789123456789999999");// more than 19 digits
        assertFalse(reservationIdValidator(reservation3.getReservationId()));

        Reservation reservation4 = new Reservation();
        reservation4.setReservationId("123456789asdfghjkn5");// 19 length with both digits and letters
        assertFalse(reservationIdValidator(reservation4.getReservationId()));
    }
}
