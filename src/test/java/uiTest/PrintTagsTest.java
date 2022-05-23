package uiTest;

import com.app.flight.Main;
import com.app.flight.controller.PrintTagsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class PrintTagsTest {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/PrintTags.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();

        PrintTagsController printTagsController = fxmlLoader.getController();
        printTagsController.next.setDisable(true);
        printTagsController.help.setVisible(false);
        printTagsController.percentage.textProperty().addListener(changeListener -> {
            printTagsController.next.setDisable((printTagsController.percentage).isDisabled());
        });
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#progressBar").queryAs(ProgressBar.class)).isVisible();
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help").isInvisible();
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next").isDisabled();
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    @Test
    void onClickNextButton(FxRobot robot) {

    }
}
