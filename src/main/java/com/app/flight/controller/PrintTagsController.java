package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author HuangHong
 * @author Zheng Han
 * @version 2.1
 */
public class PrintTagsController implements Runnable {

    public Button help;
    @FXML
    protected ProgressBar progressBar;
    @FXML
    protected Label percentage;
    @FXML
    private Button next;

    private int percent;

    /**
     * The code for button "next" in printTags.fxml
     * When click the button, change to finished.fxml
     */
    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FinishController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open printTags.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/printTags.fxml"));
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

        String path = "src/main/resources/com/app/flight/audio/printer.mp3";
        Media sound = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mediaPlayer.setAutoPlay(true);

        for (int i = 0; i <= 100; i++) {
            percent = i;
            //thread sleep for 0.5s
            try {
                progressBar.setProgress(percent / 100.0);
                Platform.runLater(() -> {
                    percentage.setText(percent + " %");
                });
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Platform.runLater(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.stop();
            percentage.setText("Success!");
        });
        next.setDisable(false);
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

    @FXML
    public void helpClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new HelpController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
