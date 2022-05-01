package com.app.flight.service.external;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LianJunhong
 */
public class Printer {
    private MediaPlayer sound() {
        String path = "src/main/resources/com/app/flight/audio/printer.mp3";
        Media sound = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        return mediaPlayer;
    }

    public Boolean print(ProgressBar progressBar, Label percentage) throws InterruptedException {
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        int percent = 0;
        Thread.sleep(50);
        MediaPlayer mediaPlayer = sound();
        for (int i = 0; i <= 100; i++) {
            percent = i;
            progressBar.setProgress(percent / 100.0);
            int finalPercent = percent;
            Platform.runLater(() -> {
                percentage.setText(finalPercent + " %");
            });
            Thread.sleep(50);
        }

        Platform.runLater(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.stop();
            percentage.setText("Success!");
            result.set(true);
        });
        return result.get();
    }
}
