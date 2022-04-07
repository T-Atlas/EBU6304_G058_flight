package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.controller.PrintTagsController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 */
public class ComingSoonController {
    public Button homeButton;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/ComingSoon.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public void home(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new SelectLanguageController().start(new Stage());
                ((Stage) (homeButton.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
