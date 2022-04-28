package serviceTest;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import org.junit.jupiter.api.Test;

public class GetPassengerTest {
    @Test
    public void lookupPassengerTest() {
        String id = "220802200005217748";
        GetPassenger getPassenger = new GetPassengerImpl();
        Passenger passenger = getPassenger.lookupPassengerById(id);
        System.out.println(passenger);

        String bookNumber = "1517539047050973184";
        System.out.println(getPassenger.lookupPassengerByBookingNumber(bookNumber));

        String s = JSON.toJSONString(passenger, JSONWriter.Feature.PrettyFormat);
        System.out.println(s);
    }
}
