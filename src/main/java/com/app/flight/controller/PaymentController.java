package com.app.flight.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSON;
import com.app.flight.Main;
import com.app.flight.entity.Food;
import com.app.flight.entity.Reservation;
import com.app.flight.util.Json;
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

import java.io.IOException;

public class PaymentController {

    @FXML
    public Button finish;
    public Button help;
    public Boolean whetherPayment = false;
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

    public void pay(String paymentMethod) {
        code.setVisible(false);
        textField.setVisible(false);
        clean.setVisible(false);

        String foodString = Json.extractJsonData(Json.FOOD_JSON_PATH);
        Food food = JSON.parseObject(foodString, Food.class);
        double fPrice = food.getFoodPrice();
        String reservationStr = Json.extractJsonData(Json.RESERVATION_JSON_PATH);
        Reservation reservation = JSON.parseObject(reservationStr, Reservation.class);
        //int price = reservation.getSeatLevel().getPrice();
        seatPrice.setText("None");
        foodPrice.setText("￡" + String.valueOf(fPrice));

        if (paymentMethod.equals("paypal") || paymentMethod.equals("alipay")) {
            whetherPayment = true;
            code.setVisible(true);
            code.setImage(new Image(String.valueOf(Main.class.getResource("image/QR.jpg"))));
        } else if (paymentMethod.equals("visa")) {
            annotation.setText("--> Please input your VISA card number:");
            textField.setVisible(true);
            clean.setVisible(true);
        }
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    public void nextClick(ActionEvent actionEvent) {
        if (!textField.getText().equals("")) {
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

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/Payment.fxml"));
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
                FXMLLoader fxmlLoader = new SelectPaymentController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void clean(ActionEvent actionEvent) {
        textField.setText("");
    }
}
