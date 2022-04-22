package serviceTest;

import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import org.junit.jupiter.api.Test;

public class GetPassengerTest {
    @Test
    public void lookupPassengerTest() {
        String id = "220802200005217748";
        GetPassenger getPassenger = new GetPassengerImpl();
        System.out.println(getPassenger.lookupPassenger(id));
    }
}
