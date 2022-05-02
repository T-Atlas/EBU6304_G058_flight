package com.app.flight.controller;

import com.app.flight.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 */
public class HelpController {
    public Button back;
    public Button call;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/Help.fxml"));
    }

    public void callButton(ActionEvent actionEvent) {
        //A pop-up window appears after the button is pressed, and the progress bar counts down to five seconds and then displays Please be patient.
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.FINISH);
        alert.setTitle("Success");
        alert.setHeaderText("Please be patient.");
        alert.showAndWait();
    }

    public void backButton(ActionEvent actionEvent) {

    }

}
