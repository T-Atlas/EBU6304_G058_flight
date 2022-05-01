package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
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
 * @author LianJunhong
 * @author HuangHong
 * @version 2.1
 */
public class InfoConfirmController {
    @FXML
    public Label num;
    @FXML
    public Button next;
    @FXML
    public Button back;

    public Passenger pRetrieve;
    public Button help;


    public void showNum(Passenger p) {
        num.setText(p.getPassengerId());
    }

    public void start(Stage stage, Passenger p) throws IOException {

        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        InfoConfirmController i = fxmlLoader.getController();
        i.showNum(p);
        i.pRetrieve = p;
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();

    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/InfoConfirm.fxml"));
    }

    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new RetrieveController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                RetrieveController retrieveController = fxmlLoader.getController();
                retrieveController.showRetrieve(pRetrieve);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void returnButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) back.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectMethodController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void helpClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new HelpController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
