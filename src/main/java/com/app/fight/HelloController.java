package com.app.fight;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author LianJunhong
 * @version 0.1
 * @date 2022.3.26
 */
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}