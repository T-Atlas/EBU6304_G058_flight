package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This code is used to finish all the steps of check-in.
 * @author Huanghong
 * @version 0.1
 * @date 2022.3.30
 */

public class FinishController  {

    @FXML
    private Button finish;

    /**
     * The code to close current page and open the first page
     */
    public void finishClick() {
        Platform.runLater(() -> {
            try {
                new SelectLanguageController().start(new Stage());
                ((Stage) (finish.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * The code for other pages to open finished.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/finished.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

}
