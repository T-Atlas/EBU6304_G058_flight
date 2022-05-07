package serviceTest;

import com.app.flight.entity.BoardingPass;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.service.impl.GetBoardingPassImpl;
import org.junit.jupiter.api.Test;

public class GetBoardingPassTest {
    GetBoardingPass getBoardingPass;
    BoardingPass boardingPass;

    @Test
    public void lookupBoardingPassTest() {
        getBoardingPass = new GetBoardingPassImpl();
        //assertEquals(,getBoardingPass.lookupBoardingPass());
    }
}


