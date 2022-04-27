package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 * @version 2.1
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
        FXMLLoader fxmlLoader = getLoader();
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

        Stage stage = (Stage) next.getScene().getWindow();
        //send selected method to next controller depending on which radiobutton is selected
        if (bookingNum.isSelected() || idNum.isSelected()) {
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new InputNumberController().getLoader();//需要修改成页面展示的controller
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    InputNumberController inputNumberController = fxmlLoader.getController();
                    inputNumberController.type = (String) method.getSelectedToggle().getUserData();
                    if (inputNumberController.type.equals("id")) {
                        inputNumberController.annotation.setText("--> Please input your ID number:");
                    } else if (inputNumberController.type.equals("booking")) {
                        inputNumberController.annotation.setText("--> Please input your booking number:");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("数据查找失败");
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new ComingSoonController().getLoader();//需要修改成页面展示的controller
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/SelectMethod.fxml"));
    }
}
