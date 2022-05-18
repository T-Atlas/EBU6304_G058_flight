package uiTest;

import com.app.flight.Main;
import com.app.flight.controller.InfoConfirmController;
import com.app.flight.entity.Passenger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class InfoConfirmTest {
    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/InfoConfirm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        InfoConfirmController i = fxmlLoader.getController();
        Passenger p = new Passenger();
        p.setPassengerId("123");
        p.setFirstName("test");
        p.setLastName("t");
        p.setAge(1);
        p.setTelephone("11111111111");
        i.showNum(p);
        i.pRetrieve = p;
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containText(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#num").queryAs(Label.class));
        Assertions.assertThat(robot.lookup("#next").queryAs(Button.class)).hasText("Next");
        Assertions.assertThat(robot.lookup("#help").queryAs(Button.class)).hasText("Help");
        Assertions.assertThat(robot.lookup("#back").queryAs(Button.class)).hasText("Return");
        ;
    }

    @Test
    void onClickHelpButton(FxRobot robot) {
        robot.clickOn("#help");
        Assertions.assertThat(robot.lookup("#call").queryAs(Button.class)).hasText("Yes");
    }

    @Test
    void onClickReturnButton(FxRobot robot) {
        robot.clickOn("#back");
        Assertions.assertThat(robot.lookup("#bookingNum").queryAs(RadioButton.class));
    }

    @Test
    void onClickNextButton(FxRobot robot) {
        robot.clickOn("#next");
        Assertions.assertThat(robot.lookup("#bookingNum").queryAs(RadioButton.class));
    }
}
