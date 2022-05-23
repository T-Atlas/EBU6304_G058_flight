package uiTest;

import com.app.flight.Main;
import com.app.flight.controller.RetrieveController;
import com.app.flight.entity.Passenger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class RetrieveTest {
    @Start
    private void start(Stage stage) throws IOException {
        Passenger pRetrieve = new Passenger();
        pRetrieve.setPassengerId("1234");
        pRetrieve.setFirstName("test");
        pRetrieve.setFirstName("t");
        pRetrieve.setAge(2);
        pRetrieve.setTelephone("11111111111");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/Retrieve.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        RetrieveController retrieveController = fxmlLoader.getController();
        retrieveController.showRetrieve(pRetrieve);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#tableView").queryAs(TableView.class));
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next").isDisabled();
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }
}
