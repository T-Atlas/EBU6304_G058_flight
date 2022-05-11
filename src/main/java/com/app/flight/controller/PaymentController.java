package com.app.flight.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alibaba.fastjson2.JSON;
import com.app.flight.Main;
import com.app.flight.entity.Food;
import com.app.flight.entity.Reservation;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.util.Json;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
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

    public static final String QR_CODE_PATH = "src/main/resources/com/app/flight/image/QR_Code/QR.jpg";
    public static final String PAYPAL_IMAGE_PATH = "src/main/resources/com/app/flight/image/QR_Code/PaypalLogo.png";
    public static final String PAYPAL_SITE = "https://www.paypal.com/ph/signin";
    public static final String ALIPAY_IMAGE_PATH = "src/main/resources/com/app/flight/image/QR_Code/AlipayLogo.png";
    public static final String ALIPAY_SITE = "https://auth.alipay.com/login/index.htm";
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

    private void generateQRCode(String url, String path) {
        QrConfig config = new QrConfig(300, 300);
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        config.setMargin(1);
        config.setImg(new File(path));
        config.setRatio(8);
        QrCodeUtil.generate(
                url, //二维码内容
                config,//附带logo
                FileUtil.newFile(QR_CODE_PATH)//写出到的文件
        );
    }

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
            seatPrice = reservation.getSeatLevel().getPrice();
        }
        this.seatPrice.setText("￡" + seatPrice);
        this.foodPrice.setText("￡" + foodPrice);

        if (paymentMethod.equals("paypal") || paymentMethod.equals("alipay")) {
            whetherPayment = true;
            code.setVisible(true);
            if (paymentMethod.equals("paypal")) {
                generateQRCode(PAYPAL_SITE, PAYPAL_IMAGE_PATH);
            } else {
                generateQRCode(ALIPAY_SITE, ALIPAY_IMAGE_PATH);
            }
            code.setImage(new Image(new File(QR_CODE_PATH).toURI().toString()));
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
