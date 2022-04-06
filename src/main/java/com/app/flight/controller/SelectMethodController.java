package com.app.flight.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * @author LianJunhong
 */
public class SelectMethodController {

    @FXML
    public RadioButton idNum;
    @FXML
    public RadioButton scan;
    @FXML
    public Label bookingNum;
    @FXML
    public ToggleGroup method;

}
