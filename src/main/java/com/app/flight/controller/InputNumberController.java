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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 * @author HuangHong
 * @version 2.1
 */
public class InputNumberController {
    public Passenger p;
    @FXML
    public TextField number;
    @FXML
    public Label annotation;
    public Button clean;
    public Button next;
    public Button help;
    public Button back;

    public Button numClean;
    public Label nameLabel;
    public Label numLabel;
    public TextField surName;

    protected String type;
    GetPassenger getPassenger = new GetPassengerImpl();

    public void submit(ActionEvent actionEvent) {
        p = getPassengerInfo(type, number.getText());
        System.out.println(p);
        Platform.runLater(() -> {
            Stage stage = (Stage) clean.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader;
                if (p != null) {
                    fxmlLoader = new InfoConfirmController().getLoader();
                } else {
                    fxmlLoader = new ComingSoonController().getLoader();
                }
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                if (p != null) {
                    InfoConfirmController i = fxmlLoader.getController();
                    i.showNum(p);
                    i.pRetrieve = p;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    protected Passenger getPassengerInfo(String type, String text) {
        Passenger passenger;
        if (type.equals("id") && (Validator.idValidator(text) || text.equals("123456"))) {
            passenger = getPassenger.lookupPassengerById(text);
        } else if (type.equals("booking") && Validator.reservationIdValidator(text)) {
            passenger = getPassenger.lookupPassengerByBookingNumber(text);
        } else {
            //TODO:页面提示输入错误
            passenger = null;
        }
        return passenger;
    }


    /**
     * The code for other pages to open InputNumber.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public void clean(ActionEvent actionEvent) {
        number.setText("");
        next.setDisable(true);
    }

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/InputNumber.fxml"));
    }

    @FXML
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

    public void returnButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectMethodController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
