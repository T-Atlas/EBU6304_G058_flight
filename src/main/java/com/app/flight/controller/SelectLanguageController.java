package com.app.flight.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This code is used to control the interface for selecting language.
 * @author LianJunhong
 * @version 0.1
 * @date 2022.3.27
 */
public class SelectLanguageController {
    public Button finish;
    public Button loginButton;
    public Button backButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onBackButtonClick() {
    }
}
