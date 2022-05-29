package uiTest;

import com.app.flight.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * @version 2.2
 * Test class for AdminLogin page
 */
@ExtendWith(ApplicationExtension.class)
public class AdminLoginTest {
    /**
     * Before all test, initiation of uploading the fxml page
     *
     * @param stage stage
     * @throws IOException IOException
     */
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/AdminLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Admin Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test for contain elements in the page
     *
     * @param robot robot
     */
    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#idTextField").queryAs(TextField.class)).isVisible();
        Assertions.assertThat(robot.lookup("#passwordTextField").queryAs(TextField.class)).isVisible();
        Assertions.assertThat(robot.lookup("#loginButton").queryAs(Button.class)).hasText("Log in");
        Assertions.assertThat(robot.lookup("#backButton").queryAs(Button.class)).hasText("Back");
    }

    /**
     * Test for clicking on Back button
     *
     * @param robot robot
     */
    @Test
    void onClickBackButton(FxRobot robot) {
        robot.clickOn("#backButton");
    }

    @Test
    void onClickLoginButton(FxRobot robot) {
        robot.clickOn("#loginButton");
    }
}
