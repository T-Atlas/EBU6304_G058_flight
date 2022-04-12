package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetReservation;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.service.temp.GetPassengerImplTemp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author LianJunhong
 * @author HuangHong
 */
public class InfoConfirmController {
    @FXML
    public Label num;
    @FXML
    public Button next;
    @FXML
    public Button back;


    public void showNum(Passenger p){
        num.setText(p.getPassengerId());
    }

    public void start(Stage stage, Passenger p) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/InfoConfirm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        InfoConfirmController i = fxmlLoader.getController();
        i.showNum(p);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();


    }

    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new RetrieveController().start(new Stage());
                ((Stage) (next.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void Return(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new InputNumberController().start(new Stage());
                ((Stage) (back.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
