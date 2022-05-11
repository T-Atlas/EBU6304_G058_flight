package com.app.flight.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.app.flight.Main;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * This code is used to finish all the steps of check-in.
 *
 * @author Huanghong
 * @version 2.1
 * @date 2022.3.30
 */

public class FinishController {

    protected static final String TAG_IMAGE_PATH = "src/main/resources/com/app/flight/image/QR_Code/TagLogo.png";
    protected static final String TAG_TXT = "src/main/resources/com/app/flight/data/printer/Tag.txt";
    protected static final String BOARDING_IMAGE_PATH = "src/main/resources/com/app/flight/image/QR_Code/BoardingPassLogo.png";
    protected static final String BOARDING_TXT = "src/main/resources/com/app/flight/data/printer/BoardingPass.txt";
    private static final String QR_CODE_PATH = "src/main/resources/com/app/flight/image/QR_Code/";
    public ImageView boardingPassCode;
    public ImageView tagCode;
    @FXML
    private Button finish;

    protected void generateQRCode(String url, String path, String type) {
        QrConfig config = new QrConfig(300, 300);
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        config.setMargin(1);
        config.setImg(new File(path));
        config.setRatio(8);
        QrCodeUtil.generate(
                url, //二维码内容
                config,//附带logo
                FileUtil.newFile(QR_CODE_PATH + type + ".jpg")//写出到的文件
        );
    }

    /**
     * The code to close current page and open the first page
     */
    public void finishClick() {
        Platform.runLater(() -> {
            Stage stage = (Stage) finish.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectLanguageController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * The code for other pages to open Ffinished.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/Finished.fxml"));
    }
}
