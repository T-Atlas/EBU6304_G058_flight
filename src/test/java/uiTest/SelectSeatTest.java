package uiTest;

import com.app.flight.Main;
import com.app.flight.controller.SelectSeatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Map;

@ExtendWith(ApplicationExtension.class)
public class SelectSeatTest {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectSeat.fxml"));
        SelectSeatController selectSeatController = fxmlLoader.getController();
        selectSeatController.flightId = "MH1234";
        Map<Integer, Map<String, Boolean>> seatMap = null;
        assert false;
        selectSeatController.showSeatMap(seatMap);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Please Select Your SeatUtil");

        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#gridPane").queryAs(GridPane.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next").isDisabled();
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }
}
