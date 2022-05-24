package uiTest;

import com.app.flight.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class FinishedTest {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/Finished.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#finish").queryAs(Button.class)).hasText("Finish");
        Assertions.assertThat(robot.lookup("#boardingPassCode").queryAs(ImageView.class)).isVisible();
        Assertions.assertThat(robot.lookup("#tagCode").queryAs(ImageView.class)).isVisible();
    }

    @Test
    void onClickFinishButton(FxRobot robot) {
        robot.clickOn("#finish");
    }
}
