package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This code is used to control the interface for selecting language.
 * @author LianJunhong
 * @version 0.1
 * @date 2022.3.27
 */
public class SelectLanguageController {
    public Button english;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    /**
     * The code to open SelectLanguage.fxml.
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectLanguage.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    public void onEnglishButtonClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new AttentionNotesController().start(new Stage());
                ((Stage) (english.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
