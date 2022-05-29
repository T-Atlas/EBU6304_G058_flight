package com.app.flight.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSON;
import com.app.flight.Main;
import com.app.flight.entity.Food;
import com.app.flight.entity.Reservation;
import com.app.flight.service.external.QRCodeGenerator;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.service.impl.SeatMapImpl;
import com.app.flight.util.Json;
import com.app.flight.util.Validator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PaymentController {


    @FXML
    private Button finish;
    @FXML
    private Button help;
    private Boolean whetherPayment = false;
    @FXML
    private ImageView code;
    @FXML
    private TextField textField;
    @FXML
    private Button clean;
    @FXML
    private Label annotation;
    @FXML
    private Label seatPrice;
    @FXML
    private Label foodPrice;


    /**
     * This method is used to show the total price of service and generate QR code.
     *
     * @param paymentMethod
     */
    public void pay(String paymentMethod) {
        code.setVisible(false);
        textField.setVisible(false);
        clean.setVisible(false);

        String foodString = Json.extractJsonData(Json.FOOD_JSON_PATH);
        Food food = JSON.parseObject(foodString, Food.class);
        double foodPrice = food.getFoodPrice();
        double seatPrice = 0;
        Reservation reservation = GetReservationImpl.lookupReservation();
        if (reservation != null) {
            seatPrice = SeatMapImpl.lookupSeatPrice();
        }
        this.seatPrice.setText("￡" + seatPrice);
        this.foodPrice.setText("￡" + foodPrice);

        if (paymentMethod.equals("paypal") || paymentMethod.equals("alipay")) {
            whetherPayment = true;
            code.setVisible(true);
            QRCodeGenerator.generatePayCode(paymentMethod);
            code.setImage(new Image(new File(QRCodeGenerator.QR_CODE_PATH + "QR.jpg").toURI().toString()));
        } else if (paymentMethod.equals("visa")) {
            annotation.setText("--> Please input your VISA card number:");
            textField.setVisible(true);
            clean.setVisible(true);
        }
    }

    /**
     * The code for other pages to open Payment.fxml
     *
     * @param stage
     * @throws IOException
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The code for button "next" to go to "PrintTags.fxml"
     * @param actionEvent
     * @throws IOException
     */
    public void nextClick(ActionEvent actionEvent) {
        if ((!textField.getText().equals("")) && Validator.visaIdValidator(textField.getText())) {
            whetherPayment = true;
        }
        if (whetherPayment) {
            Stage stage = (Stage) finish.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Pay successfully!");
            alert.showAndWait();
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new PrintTagsController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    PrintTagsController printTagsController = fxmlLoader.getController();
                    ThreadUtil.execute(printTagsController);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please pay!");
            alert.showAndWait();
        }
    }

    /**
     * This method is used to get the loader for the Payment controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/Payment.fxml"));
    }

    /**
     * This method is used to get the help page.
     *
     * @param actionEvent
     * @throws IOException
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

    /**
     * This method is called when the user clicks on the return button.
     * And it allows the user to go back to the previous scene.
     *
     * @param actionEvent the event
     * @throws IOException
     */
    public void returnButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectPaymentController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method is used to clean the user's input.
     *
     * @param actionEvent the event of clicking the button
     */
    public void clean(ActionEvent actionEvent) {
        textField.setText("");
    }
}
