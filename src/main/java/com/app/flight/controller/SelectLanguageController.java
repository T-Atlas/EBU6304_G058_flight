package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This code is used to control the interface for selecting language.
 *
 * @author LianJunhong
 * @version 0.1
 * @date 2022.3.27
 */
public class SelectLanguageController {
    @FXML
    public Button english;
    @FXML
    public Label coming;
    public Button chinese;

    @FXML
    private Button loginButton;

    /**
     * This method is used to change the language to Chinese.
     */
    @FXML
    protected void onChineseButtonClick() {
        coming.setText("Coming soon!");
    }

    /**
     * The code to close current page and open the login page
     *
     * @throws IOException
     */
    @FXML
    public void loginButtonClick() {
        Platform.runLater(() -> {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new AdminLoginController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code to open SelectLanguage.fxml.
     * @param stage
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to get the loader of SelectLanguage.fxml.
     * @throws IOException
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/SelectLanguage.fxml"));
    }

    /**
     * This method is used to change the language to English.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onEnglishButtonClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) english.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new AttentionNotesController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
}
