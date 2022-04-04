package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminWelcomeController {

    @FXML
    private Label welcomeMeg;

    @FXML
    private Button logoutButton;


    public void setWelcomeMeg(String meg) {
        this.welcomeMeg.setText(meg);
    }

    /**
     * The code to close current page and open the first page
     */
    public void logoutButtonClick() {
        Platform.runLater(() -> {
            try {
                new SelectLanguageController().start(new Stage());
                ((Stage) (logoutButton.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/adminWelcome.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome Administrator");
        stage.setScene(scene);
        stage.show();
    }
}
