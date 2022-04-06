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
 */
public class AttentionNotesController {
    @FXML
    public Button next;
    @FXML
    public CheckBox confirm;

    /**
     * if checkbox is checked, make next button able to click
     * @param actionEvent
     */
    public void check(ActionEvent actionEvent) {
        next.setDisable(!confirm.isSelected());
    }

    /**
     * The code for button "next" in "AttentionNotes.fxml"
     * When click the button, change to "InputNumber.fxml"
     */
    public void nextClick(ActionEvent actionEvent) {

        Platform.runLater(() -> {
            try {
                new PrintTagsController().start(new Stage());//需要修改成页面展示的controller
                ((Stage) (next.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open InputNumber.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/InputNumber.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

}
