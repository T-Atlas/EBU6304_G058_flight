package com.app.flight.service.external;

import cn.hutool.core.thread.ThreadUtil;
import com.app.flight.controller.SelectLanguageController;
import com.app.flight.service.Schedule;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;


/**
 * @author LianJunhong
 */
public class ScheduleTimer implements Schedule {

    static ScheduledExecutorService scheduleExec = ThreadUtil.createScheduledExecutor(1);

    static Label timerLabel;
    static ProgressBar timerProgressBar;
    @Getter
    @Setter
    static private int seconds;

    private static void init() {
        timerProgressBar = new ProgressBar();
        setSeconds(300);
    }

    public static void createSchedule() {
        init();
        scheduleExec.scheduleAtFixedRate(() -> {
            if (seconds > 0) {
                seconds--;
                timerProgressBar.setProgress(1 - (double) seconds / 300);
            } else {
                timerProgressBar.setProgress(0);
                scheduleTask();
            }
        }, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
        scheduleExec.shutdown();
    }

    private static void scheduleTask() {
        Platform.runLater(() -> {
            Stage stage = (Stage) timerProgressBar.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectLanguageController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void reset() {
        setSeconds(300);
        timerProgressBar.setProgress(1);
        timerProgressBar.setDisable(true);
    }
}
