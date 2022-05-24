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
 * @version 2.1
 * Test class for SelectPayment
 */
@ExtendWith(ApplicationExtension.class)
public class SelectPaymentTest {
    /**
     * Before all tests initiation of uploading fxml pages
     *
     * @param stage stage
     * @throws IOException IOException
     */
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectPayment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
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
        Assertions.assertThat(robot.lookup("#paypal").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#alipay").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#visa").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
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
     * test for clicking on paypal radiobutton
     *
     * @param robot robot
     */
    @Test
    void onClickPaypalRadioButton(FxRobot robot) {
        robot.clickOn("#paypal");
        Assertions.assertThat(robot.lookup("#paypal").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
    }

    /**
     * test for clicking on alipay radiobutton
     *
     * @param robot robot
     */
    @Test
    void onClickAlipayRadioButton(FxRobot robot) {
        robot.clickOn("#alipay");
        Assertions.assertThat(robot.lookup("#alipay").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
    }

    /**
     * test for clicking on visa radiobutton
     *
     * @param robot robot
     */
    @Test
    void onClickVisaRadioButton(FxRobot robot) {
        robot.clickOn("#visa");
        Assertions.assertThat(robot.lookup("#visa").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
    }
}
