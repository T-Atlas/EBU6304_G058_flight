package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 * @author HuangHong
 */
public class InputNumberController {
    @FXML
    public TextField number;
    @FXML
    public Label annotation;
    public Button clean;
    public static Passenger p;
    GetPassenger getPassenger = new GetPassengerImpl();

    public void submit(ActionEvent actionEvent) {
        p = getPassenger.lookupPassenger(number.getText());
        System.out.println(p);
        Platform.runLater(() -> {
            try {
                if (p != null) {
                    new InfoConfirmController().start(new Stage(), p);
                    ((Stage) (clean.getScene().getWindow())).close();
                } else {
                    new ComingSoonController().start(new Stage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open InputNumber.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/InputNumber.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public void clean(ActionEvent actionEvent) {
        number.setText("");
    }
}
