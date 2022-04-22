package serviceTest;

import com.app.flight.entity.BoardingPass;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.service.impl.GetBoardingPassImpl;
import org.junit.jupiter.api.Test;

public class GetBoardingPassTest {
    @Test
    public void lookupBoardingPassTest() {
        GetBoardingPass getBoardingPass = new GetBoardingPassImpl();
        BoardingPass boardingPass = getBoardingPass.lookupBoardingPass();
        System.out.println(boardingPass);
    }
}
