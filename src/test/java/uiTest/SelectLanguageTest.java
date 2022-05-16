package uiTest;

import com.app.flight.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class SelectLanguageTest {

    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectLanguage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("ico/OneworldLogo.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.toFront();

    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#chinese").queryAs(Button.class)).hasText("Chinese");
        Assertions.assertThat(robot.lookup("#english").queryAs(Button.class)).hasText("English");
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void onChineseButtonClick(FxRobot robot) {

        Assertions.assertThat(robot.lookup("#coming").queryAs(Label.class)).hasText("Select the language and you can check-in right now");
        // when:
        robot.clickOn("#chinese");
        Assertions.assertThat(robot.lookup("#coming").queryAs(Label.class)).hasText("Coming soon!");
    }
}
