package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 * @version 2.1
 */
public class AttentionNotesController {
    @FXML
    private Button next;
    @FXML
    private CheckBox confirm;

    /**
     * If checkbox is checked, make next button able to click.
     *
     * @param actionEvent the event of checkbox
     */
    @FXML
    private void check(ActionEvent actionEvent) {
        next.setDisable(!confirm.isSelected());
    }

    /**
     * The code for button "next" in "AttentionNotes.fxml"
     * When click the button, change to "InputNumber.fxml"
     *
     * @param actionEvent the event of button
     */
    @FXML
    private void nextClick(ActionEvent actionEvent) {

        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectMethodController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open InputNumber.fxml
     *
     * @param stage the stage of other pages
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Get the loader of AttentionNotes.fxml
     *
     * @return the loader of AttentionNotes.fxml
     * @throws IOException the exception to loader
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/AttentionNotes.fxml"));
    }
}
