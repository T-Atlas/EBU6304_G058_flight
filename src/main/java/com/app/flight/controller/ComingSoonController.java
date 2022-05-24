package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 * @version 2.1
 */
public class ComingSoonController {
    @FXML
    private Button homeButton;

    /**
     * This method is used to the undeveloped page.
     *
     * @param stage the stage
     * @throws IOException the IO exception
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to get the loader.
     *
     * @return the FXMLLoader
     * @throws IOException the IO exception
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/ComingSoon.fxml"));
    }

    /**
     * This method is used to go back to the home page.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void home(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) (homeButton.getScene().getWindow());
            try {
                FXMLLoader fxmlLoader = new SelectLanguageController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        });
    }
}
