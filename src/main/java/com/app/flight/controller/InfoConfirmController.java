package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 */
public class InfoConfirmController {

    Passenger p ;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/InfoConfirm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }
}
