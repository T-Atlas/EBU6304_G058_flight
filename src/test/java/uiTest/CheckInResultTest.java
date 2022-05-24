package uiTest;

import com.app.flight.controller.ResultController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class CheckInResultTest {
    @Start
    private void start(Stage stage) throws IOException {
//        String gateName = boardingPass.getFlight().getBoardingGate();
//        navigationMapController.setMap(gateName);

        FXMLLoader fxmlLoader = new ResultController().getLoader();
        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
        ResultController resultController = fxmlLoader.getController();
        resultController.showBoardingPass(false);
        stage.setTitle("Smart flight check-in kiosk");
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#name").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#departure").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#destination").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#boardingGate").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#seat").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#foodType").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#detail").queryAs(Button.class)).hasText("Details");
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next");
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    @Test
//bug
    void onClickDetailsButton(FxRobot robot) {
        robot.clickOn("#detail");
        Assertions.assertThat(robot.lookup("#back").queryAs(Button.class)).hasText("Return");
    }
}