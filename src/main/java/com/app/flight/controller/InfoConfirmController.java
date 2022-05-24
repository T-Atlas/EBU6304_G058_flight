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
    protected Passenger passengerRetrieve;
    @FXML
    private Label num;
    @FXML
    private Button next;
    @FXML
    private Button back;
    @FXML
    private Button help;

    /**
     * This setter is for test class to set the passenger.
     *
     * @param passengerRetrieve the retrieve passenger
     */
    public void setPassengerRetrieve(Passenger passengerRetrieve) {
        this.passengerRetrieve = passengerRetrieve;
    }

    /**
     * This method is used to show the number of the passenger.
     *
     * @param p the passenger
     */
    public void showNum(Passenger p) {
        num.setText(p.getPassengerId());
    }

    /**
     * Call this method will start InfoConfirm page.
     *
     * @param stage the stage to show the page
     * @param p     the passenger
     * @throws IOException the exception
     */
    public void start(Stage stage, Passenger p) throws IOException {

        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        InfoConfirmController i = fxmlLoader.getController();
        i.showNum(p);
        i.passengerRetrieve = p;
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method is used to get the loader of the page.
     *
     * @return the FXMLLoader of the page
     * @throws IOException the exception
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/InfoConfirm.fxml"));
    }

    /**
     * This method is called when the user clicks on the next button.
     *
     * @param actionEvent the event
     */
    @FXML
    private void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new RetrieveController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                RetrieveController retrieveController = fxmlLoader.getController();
                retrieveController.showRetrieve(passengerRetrieve);
            } catch (IOException e) {
                e.printStackTrace();
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
            Stage stage = (Stage) back.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectMethodController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
}
