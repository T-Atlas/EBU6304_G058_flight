package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.util.Common;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author HuangHong
 */
public class SelectPaymentController {
    @FXML
    public RadioButton paypal;
    @FXML
    public RadioButton alipay;
    @FXML
    public RadioButton visa;
    public ToggleGroup method;
    public Button next;
    public Button help;

    /**
     * The code for other pages to open SelectPayment.fxml
     *
     * @param stage the stage
     * @throws IOException the exception
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to check which payment method the user has selected.
     *
     * @return payment
     */
    public String check() {
        paypal.setUserData("paypal");
        alipay.setUserData("alipay");
        visa.setUserData("visa");

        String payment = method.getSelectedToggle().getUserData().toString();
        if (payment != null) {
            next.setDisable(false);
        }
        return payment;
    }

    /**
     * The method is used to go to the next page.
     *
     * @param actionEvent
     */
    public void nextClick(ActionEvent actionEvent) {
        Stage stage = (Stage) next.getScene().getWindow();
        if (check() != null) {
            Common.setSelectType((String) method.getSelectedToggle().getUserData());
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new PaymentController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    PaymentController paymentController = fxmlLoader.getController();
                    paymentController.pay(check());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please select a payment method!");
            alert.showAndWait();
        }

    }

    /**
     * This method is used to get the loader for the SelectPayment controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/SelectPayment.fxml"));
    }

    /**
     * This method is used to get the help page.
     *
     * @param actionEvent
     */
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
}
