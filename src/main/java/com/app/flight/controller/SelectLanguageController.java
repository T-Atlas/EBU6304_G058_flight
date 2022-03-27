package com.app.flight.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This code is used to control the interface for selecting language.
 * @author LianJunhong
 * @version 0.1
 * @date 2022.3.27
 */
public class SelectLanguageController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}
