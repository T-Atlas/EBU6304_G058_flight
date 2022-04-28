package serviceTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
        String s = JSON.toJSONString(passenger, SerializerFeature.PrettyFormat);
        System.out.println(s);
    }
}
