package com.app.flight.service.external;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSON;
import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.util.Json;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * @author LianJunhong
 * @author SongBo
 */
public class Printer {
    private static final String BOARDING_PASS_TXT_PATH = "src/main/resources/com/app/flight/data/printer/BoardingPass.txt";

    private MediaPlayer sound() {
        String path = "src/main/resources/com/app/flight/audio/printer.mp3";
        Media sound = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        return mediaPlayer;
    }


    public static void printBoardingPass(String jsonFilePath, String boardingPassFile) {
        File jsonFile = new File(jsonFilePath);
        String jsonData = Json.extractJsonData(jsonFile);
        BoardingPass boardingPass = JSON.parseObject(jsonData, BoardingPass.class);
        Flight flight = boardingPass.getFlight();
        Passenger passenger = boardingPass.getPassenger();
        LocalDateTime boardingTime = flight.getBoardingTime();
        String firstName = passenger.getFirstName();
        String lastName = passenger.getLastName();
        String seatNo = boardingPass.getSeatNo();
        if (seatNo.length() == 2) {
            seatNo = seatNo + " ";
        }
        String boardingGate = flight.getBoardingGate();
        if (boardingGate.length() == 2) {
            boardingGate = boardingGate + " ";
        }
        int dayOfMonth = boardingTime.getDayOfMonth();
        Month month = boardingTime.getMonth();
        String hour = String.valueOf(boardingTime.getHour());
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        String minute = String.valueOf(boardingTime.getMinute());
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        String destination = flight.getDestination();
        StringBuilder dateSpaces = new StringBuilder();
        dateSpaces.append(" ".repeat(18 - String.valueOf(dayOfMonth).length() - month.name().length()));
        StringBuilder nameSpaces = new StringBuilder();
        nameSpaces.append(" ".repeat(Math.max(0, 18 - firstName.length() - lastName.length())));
        StringBuilder destSpaces = new StringBuilder();
        destSpaces.append(" ".repeat(Math.max(0, 19 - destination.length())));
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(boardingPassFile)));
            out.write("   ________BOARDING_PASS________\n" +
                    " / \\                            \\.\n" +
                    "|   |                           |.\n" +
                    " \\__|                           |.\n" +
                    "    |  DATE: " + dayOfMonth + " " + month + dateSpaces + "|.\n" +
                    "    |  FLIGHT: " + flight.getFlightId() + "           |.\n" +
                    "    |  NAME: " + firstName + " " + lastName + nameSpaces + "|.\n" +
                    "    |  SEAT: " + seatNo + "                |.\n" +
                    "    |  GATE: " + boardingGate + "                |.\n" +
                    "    |  BD TIME: " + hour + ":" + minute + "           |.\n" +
                    "    |  DEST: " + destination + destSpaces + "|.\n" +
                    "    |                           |.\n" +
                    "    |                           |.\n" +
                    "    |   ________________________|____\n" +
                    "    |  /                            /.\n" +
                    "    \\_/Group58_____________________/.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean print(ProgressBar progressBar, Label percentage) {
        int percent = 0;
        ThreadUtil.sleep(50);
        MediaPlayer mediaPlayer = sound();
        printBoardingPass(Json.BOARDING_PASS_JSON_PATH, BOARDING_PASS_TXT_PATH);
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
