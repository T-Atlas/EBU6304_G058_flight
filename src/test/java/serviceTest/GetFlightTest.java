package serviceTest;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.app.flight.entity.Flight;
import com.app.flight.service.GetFlight;
import com.app.flight.service.impl.GetFlightImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFlightTest {
    @Test
    public void lookupFlightTest() {
        String flightId = "MH8633";
        GetFlight getFlight = new GetFlightImpl();
        Flight flight = new Flight();
        flight.setFlightId(flightId);
        flight.setDeparture("Beijing");
        flight.setDestination("Hainan");
        flight.setBoardingGate("B08");
        flight.setBoardingTime(LocalDateTime.of(2022, 9, 11, 7, 5));
        flight.setDepartureTime(LocalDateTime.of(2022, 9, 11, 9, 55));
        flight.setArrivalTime(LocalDateTime.of(2022, 9, 11, 12, 55));
        assertEquals(flight, getFlight.lookupFlight(flightId));

        String s = JSON.toJSONString(flight, JSONWriter.Feature.PrettyFormat);
        assertEquals(s, JSON.toJSONString(flight, JSONWriter.Feature.PrettyFormat));
    }
}
