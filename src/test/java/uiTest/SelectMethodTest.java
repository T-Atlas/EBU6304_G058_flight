package uiTest;

import com.app.flight.controller.SelectMethodController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class SelectMethodTest {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new SelectMethodController().getLoader();
        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
        stage.setTitle("Welcome!");
        stage.show();
    }

    @Test
        //@TestMethodOrder()
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#bookingNum").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#idNum").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#scan").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next").isDisabled();
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    @Test
    void onClickBookingRadioButton(FxRobot robot) {
        robot.clickOn("#bookingNum");
        Assertions.assertThat(robot.lookup("#bookingNum").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
        Assertions.assertThat(robot.lookup("#number").queryAs(TextField.class)).isVisible();
    }

    @Test
    void onClickIdRadioButton(FxRobot robot) {
        robot.clickOn("#idNum");
        Assertions.assertThat(robot.lookup("#idNum").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
        Assertions.assertThat(robot.lookup("#surName").queryAs(TextField.class)).isVisible();
    }

    @Test
    void onClickScanRadioButton(FxRobot robot) {
        robot.clickOn("#scan");
        Assertions.assertThat(robot.lookup("#scan").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
        Assertions.assertThat(robot.lookup("#annotation").queryAs(Text.class)).isVisible();
    }
}
