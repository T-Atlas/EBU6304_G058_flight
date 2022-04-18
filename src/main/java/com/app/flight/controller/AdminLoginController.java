package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Admin;
import com.app.flight.util.Csv;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This code is used to finish all the steps of check-in.
 *
 * @author Huanghong
 * @version 0.1
 * @date 2022.3.30
 */
public class AdminLoginController {

    @FXML
    private Button backButton;

    @FXML
    private TextField idTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    /**
     * The code to close current page and open the "welcome admin" page
     */
    public void loginButtonClick() {
        Platform.runLater(() -> {
            Admin admin = new Admin();
            admin.setId(Integer.parseInt(idTextField.getText()));
            admin.setPassword(passwordTextField.getText());
            admin = (Admin) Csv.checkCsv(admin, "src/main/resources/com/app/flight/data/csv/Admin.csv");
            if (admin != null) {
                try {
                    admin.setName("Jack");
                    String meg = "Welcome, Administrator " + admin.getName() + "!";
                    new AdminWelcomeController().start(new Stage(), meg);
                    ((Stage) (loginButton.getScene().getWindow())).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * The code to close current page and open the "select language" page
     */
    public void backButtonClick() {
        Platform.runLater(() -> {
            try {
                new SelectLanguageController().start(new Stage());
                ((Stage) (backButton.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open AdminLogin.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/AdminLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Admin Login");
        stage.setScene(scene);
        stage.show();
    }
}