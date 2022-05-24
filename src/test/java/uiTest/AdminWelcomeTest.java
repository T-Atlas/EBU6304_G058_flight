package uiTest;

import com.app.flight.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class AdminWelcomeTest {
    @Start
    private void start(Stage stage) throws IOException {
        //String meg = "admin";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/AdminWelcome.fxml"));
        //stage.setTitle(meg);
        //AdminWelcomeController adminWelcomeController = fxmlLoader.getController();
        //adminWelcomeController.setWelcomeMeg(meg);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#welcomeMeg").queryAs(Label.class)).hasText("Welcome, Administrator XXX!");
        Assertions.assertThat(robot.lookup("#logoutButton").queryAs(Button.class)).hasText("Log out");
    }
}
