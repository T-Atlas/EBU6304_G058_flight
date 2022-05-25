package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.service.external.Printer;
import com.app.flight.service.external.QRCodeGenerator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author HuangHong
 * @author Zheng Han
 * @author LianJunhong
 * @version 3.5
 */
public class PrintTagsController implements Runnable {

    public Button help;

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @FXML
    protected ProgressBar progressBar;
    @FXML
    public Label percentage;
    @FXML
    public
    Button next;

    private int percent;

    /**
     * The code for button "next" in PrintTags.fxml
     * When click the button, change to Finished.fxml
     */
    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FinishController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                QRCodeGenerator.generateTagCode();
                QRCodeGenerator.generateBoardingPassCode();
                FinishController finishController = fxmlLoader.getController();
                finishController.tagCode.setImage(new Image(new File(QRCodeGenerator.QR_CODE_PATH + "TagQR.jpg").toURI().toString()));
                finishController.boardingPassCode.setImage(new Image(new File(QRCodeGenerator.QR_CODE_PATH + "BoardingPassQR.jpg").toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open PrintTags.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to get the loader for the PrintTags controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/PrintTags.fxml"));
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        next.setDisable(true);
        help.setVisible(false);
        Printer printer = new Printer();
        Boolean result;
        result = printer.print(progressBar, percentage);
        if (result) {
            next.setDisable(false);
            help.setVisible(true);
        } else {
            Platform.runLater(() -> {
                percentage.setText("Printing failed");
                System.out.println("Failed to print");
            });
        }
    }

    /**
     * Get the percentage of the progress bar
     *
     * @return the percentage of the progress bar
     */
    public int getPercent() {
        return percent;
    }

    /**
     * Set the percentage of the progress bar
     *
     * @param percent the percentage of the progress bar
     */
    public void setPercent(int percent) {
        this.percent = percent;
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
