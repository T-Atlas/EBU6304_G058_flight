package com.app.flight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Updated the code of its own version.
 *
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectLanguage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("ico/OneworldLogo.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}