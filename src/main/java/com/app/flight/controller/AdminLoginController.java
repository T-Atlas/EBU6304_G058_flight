package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.service.impl.AdminImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This code is used to finish all the steps of check-in.
 *
 * @author Huanghong
 * @version 2.1
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
     * The code to close current page and open the "AdminView" page
     */
    public void loginButtonClick() {
        Platform.runLater(() -> {
            AdminImpl adminImpl = new AdminImpl();
            if (passwordTextField.getText().equals(adminImpl.getPassword(idTextField.getText()))) {
                try {
                    FXMLLoader fxmlLoader = new AdminViewController().getLoader();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    AdminViewController adminViewController = fxmlLoader.getController();

                    String meg = "Welcome, Administrator " + adminImpl.getName(idTextField.getText()) + "!";
                    adminViewController.setWelcomeMeg(meg);
                    adminViewController.initializeTableView();
                    adminViewController.comboBox.getItems().addAll(new AdminImpl().getFlightId());
                    adminViewController.comboBox.getSelectionModel().selectedItemProperty().addListener(
                            (observable, oldValue, newValue) -> adminViewController.onFlightSelect(newValue)
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please try again");
                alert.setTitle("Error");
                alert.setHeaderText("Wrong Password");
                alert.showAndWait();
            }
        });
    }

    /**
     * The code to close current page and open the "select language" page
     */
    public void backButtonClick() {
        Platform.runLater(() -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectLanguageController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Admin Login");
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/AdminLogin.fxml"));
    }
}
