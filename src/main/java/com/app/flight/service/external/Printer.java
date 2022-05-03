package com.app.flight.service.external;

import cn.hutool.core.thread.ThreadUtil;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

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

    public Boolean print(ProgressBar progressBar, Label percentage) {
        int percent = 0;
        ThreadUtil.sleep(50);
        MediaPlayer mediaPlayer = sound();
        for (int i = 0; i <= 100; i++) {
            percent = i;
            progressBar.setProgress(percent / 100.0);
            int finalPercent = percent;
            Platform.runLater(() -> {
                percentage.setText(finalPercent + " %");
            });
            ThreadUtil.sleep(50);
        }

        Platform.runLater(() -> {
            ThreadUtil.sleep(100);
            mediaPlayer.stop();
            percentage.setText("Success!");
        });
        return true;
    }
}
