package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import com.app.flight.util.Validator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author LianJunhong
 */
public class ScanInstructionController {
    @FXML
    public MediaView mediaView;
    public Button help;
    public Button back;

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/ScanInstruction.fxml"));
    }

    public void checkIdNumber(String idNumber, Stage stage) {
        if (Validator.idValidator(idNumber) || idNumber.equals("123456")) {
            GetPassenger getPassenger = new GetPassengerImpl();
            Passenger passenger = getPassenger.lookupPassengerById(idNumber);

            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader;
                    if (passenger != null) {
                        fxmlLoader = new InfoConfirmController().getLoader();
                    } else {
                        fxmlLoader = new ComingSoonController().getLoader();
                    }
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    if (passenger != null) {
                        InfoConfirmController i = fxmlLoader.getController();
                        i.showNum(passenger);
                        i.pRetrieve = passenger;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            //TODO:页面提示输入错误
        }
    }

    public void helpClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new HelpController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                HelpController helpController = fxmlLoader.getController();
                helpController.setControllerName(this.getClass().getSimpleName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void back(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) mediaView.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectMethodController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
