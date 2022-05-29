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

/**
 * @author JiaBoran
 * @version 2.0
 * Test class for SelectFoodType
 */
@ExtendWith(ApplicationExtension.class)
public class SelectFoodTypeTest {
    /**
     * Before all tests initiation of uploading fxml pages
     *
     * @param stage stage
     * @throws IOException IOException
     */
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectFoodType.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * test for containing elements in the page
     *
     * @param robot robot
     */
    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#r1").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#r2").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#r3").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next");
    }

    /**
     * test for clicking help button
     *
     * @param robot robot
     */
    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    /**
     * test for clicking on first radiobutton
     *
     * @param robot robot
     */
    @Test
    void onClickFirstRadioButton(FxRobot robot) {
        robot.clickOn("#r1");
        Assertions.assertThat(robot.lookup("#r1").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
    }

    /**
     * test for clicking on second radiobutton
     *
     * @param robot robot
     */
    @Test
    void onClickSecondRadioButton(FxRobot robot) {
        robot.clickOn("#r2");
        Assertions.assertThat(robot.lookup("#r2").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
    }

    /**
     * test for clicking on third radiobutton
     *
     * @param robot robot
     */
    @Test
    void onClickThirdRadioButton(FxRobot robot) {
        robot.clickOn("#r3");
        Assertions.assertThat(robot.lookup("#r3").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
    }
}

