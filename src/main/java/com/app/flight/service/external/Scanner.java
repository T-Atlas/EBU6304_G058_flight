package com.app.flight.service.external;

import com.app.flight.controller.ScanInstructionController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author ZhengHan
 * @author LianJunhong
 */
public class Scanner implements Runnable {

    public ScanInstructionController scanInstructionController;
    public Stage stage;

    /**
     * Use to play the tutorial video
     *
     * @return the mediaPlayer
     */
    public MediaPlayer playVideo() {
        File file = new File("data/audio/video/scan.mp4");
        Media media = new Media(file.getAbsoluteFile().toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        return mediaPlayer;
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
        java.util.Scanner consoleScanner = new java.util.Scanner(System.in);
        String idNumber = consoleScanner.nextLine();
        this.scanInstructionController.checkIdNumber(idNumber, this.stage);
    }

    /**
     * Use to set the consoleScanner
     *
     * @param scanInstructionController the scanInstructionController
     * @param stage                     the stage
     */
    public void ConsoleScanner(ScanInstructionController scanInstructionController, Stage stage) {
        this.scanInstructionController = scanInstructionController;
        this.stage = stage;
    }
}
