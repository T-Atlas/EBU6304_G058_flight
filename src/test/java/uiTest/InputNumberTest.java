package uiTest;

import com.app.flight.Main;
import com.app.flight.controller.InputNumberController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
 * @version 2.1
 * Test class for InputNumber
 */
@ExtendWith(ApplicationExtension.class)
public class InputNumberTest {
    /**
     * Before all tests initiation of uploading fxml page
     *
     * @param stage stage
     * @throws IOException IOException
     */
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/InputNumber.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);

        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
        InputNumberController inputNumberController = fxmlLoader.getController();
        inputNumberController.next.setDisable(true);
        inputNumberController.type = "id";
        inputNumberController.number.textProperty().addListener(changeListener -> {
            inputNumberController.next.setDisable((inputNumberController.number.getText().length() <= 0) || (inputNumberController.surName.getText().length() <= 0));
        });
        inputNumberController.surName.textProperty().addListener(changeListener -> {
            inputNumberController.next.setDisable((inputNumberController.number.getText().length() <= 0) || (inputNumberController.surName.getText().length() <= 0));
        });
    }

    /**
     * test for containing elements in the page
     *
     * @param robot robot
     */
    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#number").queryAs(TextField.class)).isVisible();
        Assertions.assertThat(robot.lookup("#surName").queryAs(TextField.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#back").queryAs(Button.class)).hasText("Return");
        ;
        Assertions.assertThat(robot.lookup("#clean").queryAs(Button.class)).isVisible();
        Assertions.assertThat(robot.lookup("#nameClean").queryAs(Button.class)).isVisible();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next").isDisabled();
    }

    /**
     * test for clicking on help button
     *
     * @param robot robot
     */
    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    /**
     * test for clicking return button
     *
     * @param robot robot
     */
    @Test
    void onClickReturnButton(FxRobot robot) {
        robot.clickOn("#back");
        Assertions.assertThat(robot.lookup("#bookingNum").queryAs(RadioButton.class)).isVisible();
    }

    /**
     * test for cleaning text
     *
     * @param robot robot
     */
    @Test
    void cleanText(FxRobot robot) {
        robot.clickOn("#number");
        Assertions.assertThat(robot.write("1517539047050973184")).isNotNull();
        robot.clickOn("#clean");
        Assertions.assertThat(robot.lookup("#number").queryAs(TextField.class)).hasText("");

        robot.clickOn("#surName");
        Assertions.assertThat(robot.write("Jun")).isNotNull();
        robot.clickOn("#nameClean");
        Assertions.assertThat(robot.lookup("#surName").queryAs(TextField.class)).hasText("");
    }

    /**
     * test for inputting text
     *
     * @param robot robot
     */
    @Test
    void inputText(FxRobot robot) {
        robot.clickOn("#number");
        Assertions.assertThat(robot.write("1517539047050973184")).isNotNull();

        robot.clickOn("#surName");
        Assertions.assertThat(robot.write("Jun")).isNotNull();
    }
}
