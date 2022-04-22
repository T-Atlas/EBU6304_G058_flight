package serviceTest;

import com.app.flight.entity.Flight;
import com.app.flight.service.impl.GetFlightImpl;
import org.junit.jupiter.api.Test;

public class GetFlightTest {
    @Test
    public void lookupFlightTest() {
        GetFlightImpl getFlight = new GetFlightImpl();
        Flight mu1234 = getFlight.lookupFlight("MU1234");
        System.out.println(mu1234);
    }
}
