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
 * @version 2.1
 */
public class InputNumberController {
    public Passenger p;
    @FXML
    public TextField number;
    @FXML
    public Label annotation;
    public Button clean;
    GetPassenger getPassenger = new GetPassengerImpl();

    public void submit(ActionEvent actionEvent) {
        p = getPassenger.lookupPassenger(number.getText());
        System.out.println(p);
        Platform.runLater(() -> {
            Stage stage = (Stage) clean.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader;
                if (p != null) {
                    fxmlLoader = new InfoConfirmController().getLoader();

                } else {
                    fxmlLoader = new ComingSoonController().getLoader();
                }
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                InfoConfirmController i = fxmlLoader.getController();
                i.showNum(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open InputNumber.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public void clean(ActionEvent actionEvent) {
        number.setText("");
    }

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/InputNumber.fxml"));
    }
}
