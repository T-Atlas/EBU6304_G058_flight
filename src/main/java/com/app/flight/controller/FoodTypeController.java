package com.app.flight.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This code is used to select the food type.
 * @author HuangHong
 * @version 0.1
 * @date 2022.3.31
 */
public class FoodTypeController {

    @FXML
    private Button next;

    /**
     * The code for button "next" in ” SelectFoodType.fxml“
     * When click the button, change to “Check-In.fxml”
     */
    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new FinishController().start(new Stage());//需要修改成页面展示的controller
                ((Stage) (next.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void isSelected(){
        
    }

}
