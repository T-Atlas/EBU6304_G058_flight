package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This code is used to finish all the steps of check-in.
 *
 * @author Huanghong
 * @version 2.1
 * @date 2022.3.30
 */

public class FinishController {
    @FXML
    protected ImageView boardingPassCode;
    @FXML
    protected ImageView tagCode;
    @FXML
    private Button finish;


    /**
     * The code to close current page and open the first page
     */
    @FXML
    private void finishClick() {
        Platform.runLater(() -> {
            Stage stage = (Stage) finish.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectLanguageController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * The code for other pages to open Finished.fxml
     *
     * @param stage the stage to show the page
     * @throws IOException if the file is not found
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The code for get the loader of the page
     *
     * @return the loader of the page
     * @throws IOException if the file is not found
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/Finished.fxml"));
    }
}
