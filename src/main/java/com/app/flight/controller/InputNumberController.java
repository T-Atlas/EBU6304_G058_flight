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
    @FXML
    public TextField number;
    private final GetPassenger getPassenger = new GetPassengerImpl();
    @FXML
    public TextField surName;
    @FXML
    public String type;
    @FXML
    protected Label annotation;
    @FXML
    protected Button clean;
    @FXML
    public Button next;
    @FXML
    protected Button help;
    @FXML
    protected Button back;
    @FXML
    protected Button nameClean;
    @FXML
    protected Label nameLabel;
    @FXML
    protected Label numLabel;
    @FXML
    private Label attention;
    private Passenger p;

    /**
     * This method is used to check the input number.
     * If the input number is valid, the next page will be opened.
     *
     * @param actionEvent the event of clicking the button
     */
    public void submit(ActionEvent actionEvent) {
        p = getPassengerInfo(type, number.getText(), surName.getText());
        System.out.println(p);
        Platform.runLater(() -> {
            Stage stage = (Stage) clean.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader;
                if (p != null) {
                    fxmlLoader = new InfoConfirmController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    InfoConfirmController i = fxmlLoader.getController();
                    i.showNum(p);
                    i.passengerRetrieve = p;
                } else {
                    number.setText("");
                    surName.setText("");
                    next.setDisable(true);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method is used to get the passenger information.
     * It will check the input number and the passenger name are valid.
     * And if the input number and the passenger name are valid, the passenger information will be searched by type.
     *
     * @param type the type of the method
     * @param id   the passenger input number
     * @param name the passenger name
     * @return the passenger object with correspond information
     */
    private Passenger getPassengerInfo(String type, String id, String name) {
        Passenger passenger;
        if (type.equals("id") && (Validator.idValidator(id) || id.equals("123456"))) {
            passenger = getPassenger.lookupPassengerById(id);
            if (passenger == null) {
                attention.setText("Passenger not found.");
            } else if (!passenger.getLastName().equals(name)) {
                attention.setText("Surname doesn't match the ID number.");
                passenger = null;
            }
        } else if (type.equals("booking") && Validator.reservationIdValidator(id)) {
            passenger = getPassenger.lookupPassengerByBookingNumber(id);
        } else {
            attention.setText("Invalid number.");
            passenger = null;
        }
        return passenger;
    }


    /**
     * The code for other pages to open InputNumber.fxml
     *
     * @param stage the stage of the page
     * @throws IOException the exception to loading the page
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to clear the input number.
     *
     * @param actionEvent the event of clicking the button
     */
    @FXML
    private void clean(ActionEvent actionEvent) {
        number.setText("");
        next.setDisable(true);
    }

    /**
     * This method is used to clear the input name.
     *
     * @param actionEvent the event of clicking the button
     */
    @FXML
    private void nameClean(ActionEvent actionEvent) {
        surName.setText("");
        next.setDisable(true);
    }

    /**
     * This method is used to get the loader of the page.
     *
     * @return the FXMLLoader of the page
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/InputNumber.fxml"));
    }

    /**
     * This method is used when help button is clicked.
     *
     * @param actionEvent the event of clicking the button
     */
    @FXML
    private void helpClick(ActionEvent actionEvent) {
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
     * This method is called when the user clicks on the back button.
     * And it allows the user to go back to the previous scene.
     *
     * @param actionEvent the event
     */
    @FXML
    private void returnButton(ActionEvent actionEvent) {
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
