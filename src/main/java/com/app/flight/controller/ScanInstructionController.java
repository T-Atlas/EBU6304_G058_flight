package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

import static cn.hutool.core.util.IdcardUtil.isValidCard;


/**
 * Help passengers to use the scanning feature
 *
 * @author LianJunhong
 * @author zhenghan
 */
public class ScanInstructionController {
    @FXML
    public MediaView mediaView;
    public Button help;
    public Button back;

    /**
     * This method is used to get the loader for the ScanInstruction controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/ScanInstruction.fxml"));
    }

    /**
     * This method is used to check the id number of the id card.
     *
     * @param idNumber the input ID number
     * @param stage    stage
     */
    public void checkIdNumber(String idNumber, Stage stage) {
        if (isValidCard(idNumber) || idNumber.equals("123456")) {
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
                        i.passengerRetrieve = passenger;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            //TODO:页面提示输入错误
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new ComingSoonController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * This method is used to get the help page.
     *
     * @param actionEvent actionEvent
     */
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

    /**
     * This method is used to return to the previous page.
     *
     * @param actionEvent actionEvent
     */
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
