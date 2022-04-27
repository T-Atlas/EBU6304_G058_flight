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

/**
 * @author Zheng Han
 * @version 2.1
 */
public class AdminWelcomeController {

    @FXML
    private Label welcomeMeg;

    @FXML
    private Button logoutButton;

    public void setWelcomeMeg(String meg) {
        welcomeMeg.setText(meg);
    }

    /**
     * The code to close current page and open the first page
     */
    public void logoutButtonClick() {
        Platform.runLater(() -> {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectLanguageController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void start(Stage stage, String meg) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle(meg);
        AdminWelcomeController adminWelcomeController = fxmlLoader.getController();
        adminWelcomeController.setWelcomeMeg(meg);
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/AdminWelcome.fxml"));
    }
}
