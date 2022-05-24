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
public class SelectPaymentTest {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectPayment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#paypal").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#alipay").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#visa").queryAs(RadioButton.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next").isDisabled();
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    @Test
    void onClickPaypalRadioButton(FxRobot robot) {
        robot.clickOn("#paypal");
        Assertions.assertThat(robot.lookup("#paypal").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
    }

    @Test
    void onClickAlipayRadioButton(FxRobot robot) {
        robot.clickOn("#alipay");
        Assertions.assertThat(robot.lookup("#alipay").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
    }

    @Test
    void onClickVisaRadioButton(FxRobot robot) {
        robot.clickOn("#visa");
        Assertions.assertThat(robot.lookup("#visa").queryAs(RadioButton.class)).isFocused();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).isEnabled();
        robot.clickOn("#next");
    }
}
