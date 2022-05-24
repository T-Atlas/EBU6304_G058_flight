package uiTest;

import com.app.flight.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class SelectFoodTypeTest {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectFoodType.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#r1").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#r2").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#r3").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next");
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    @Test
    void onClickFirstRadioButton(FxRobot robot) {
        robot.clickOn("#r1");
        Assertions.assertThat(robot.lookup("#r1").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
    }

    @Test
    void onClickSecondRadioButton(FxRobot robot) {
        robot.clickOn("#r2");
        Assertions.assertThat(robot.lookup("#r2").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
    }

    @Test
    void onClickThirdRadioButton(FxRobot robot) {
        robot.clickOn("#r3");
        Assertions.assertThat(robot.lookup("#r3").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
    }
}

