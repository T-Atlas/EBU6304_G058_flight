package uiTest;

import com.app.flight.controller.PaymentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

/**
 * @author JiaBoran
 * @version 2.3
 * Test class for Payment
 */
@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentTest {
    /**
     * Beofore all tests initiation of uploading fxml page
     *
     * @param stage stage
     * @throws IOException IOException
     */
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PaymentController().getLoader();
        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
        PaymentController paymentController = fxmlLoader.getController();
        paymentController.pay("alipay");
        stage.setTitle("Welcome!");
        stage.show();
    }

    /**
     * test for containing elements in the page
     *
     * @param robot robot
     */
    @Test
    @Order(1)
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#return").queryAs(Button.class)).hasText("Return");
        Assertions.assertThat(robot.lookup("#annotation").queryAs(Label.class)).hasText("--> Please scan the QR code:");
        Assertions.assertThat(robot.lookup("#code").queryAs(ImageView.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#finish").queryAs(Button.class)).hasText("Next");
    }

    /**
     * test for clicking on help button
     *
     * @param robot robot
     */
    @Test
    @Order(2)
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    /**
     * test on clicking on help button
     *
     * @param robot robot
     */
    @Test
    @Order(3)
    void onClickNextButton(FxRobot robot) {
        robot.clickOn("#finish");
    }
}
