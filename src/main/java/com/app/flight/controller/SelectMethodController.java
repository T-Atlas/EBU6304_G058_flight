package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 */
public class SelectMethodController {

    @FXML
    public RadioButton idNum;
    @FXML
    public RadioButton scan;
    @FXML
    public RadioButton bookingNum;
    @FXML
    public ToggleGroup method;
    public Button next;


    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectMethod.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    public void check(ActionEvent actionEvent) {
        //if radiobutton in toggle-group is selected,make next button enable
        if (method.getSelectedToggle() != null) {
            next.setDisable(false);
        }
    }

    public void nextClick(ActionEvent actionEvent) {

        idNum.setUserData("id");
        bookingNum.setUserData("booking");
        scan.setUserData("scan");

        //send selected method to next controller depending on which radiobutton is selected
        if (method.getSelectedToggle() == idNum ||method.getSelectedToggle() == bookingNum) {
            Platform.runLater(() -> {
                try {
                    Stage stage = new Stage();
                    new InputNumberController().start(stage);//需要修改成页面展示的controller
                    //TODO:call labelText and sent userdata to next page
                    ((Stage) (next.getScene().getWindow())).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else if (method.getSelectedToggle() == scan) {
            Platform.runLater(() -> {
                try {
                    new SelectLanguageController().start(new Stage());//需要修改成页面展示的controller
                    ((Stage) (next.getScene().getWindow())).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
