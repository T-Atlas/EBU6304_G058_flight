package com.app.flight.service.external;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSON;
import com.app.flight.entity.BoardingPass;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.util.Csv;
import com.app.flight.util.Json;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * @author LianJunhong
 * @author SongBo
 */
public class Printer {
    private static final String BOARDING_PASS_TXT_PATH = "data/printer/BoardingPass.txt";
    private static final String TAG_TXT_PATH = "data/printer/Tag.txt";
    public static StringBuilder boardingPassData = new StringBuilder();
    public static StringBuilder tagData = new StringBuilder();


    public static void printBoardingPass(String jsonFilePath, String boardingPassFilePath) {
        String jsonData = Json.extractJsonData(jsonFilePath);
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
        boardingPassData.delete(0, boardingPassData.length());
        boardingPassData.append("DATE: ").append(dayOfMonth).append(" ").append(month).append("\n")
                .append("FLIGHT: ").append(flight.getFlightId()).append("\n")
                .append("NAME: ").append(firstName).append(" ").append(lastName).append("\n")
                .append("SEAT: ").append(seatNo).append("\n")
                .append("GATE: ").append(boardingGate).append("\n")
                .append("BD TIME: ").append(hour).append(":").append(minute).append("\n")
                .append("DEST: ").append(destination).append("\n");
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(boardingPassFilePath)));
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

    public static void printTag(String jsonFilePath, String tagFilePath) {
        String jsonData = Json.extractJsonData(jsonFilePath);
        BoardingPass boardingPass = JSON.parseObject(jsonData, BoardingPass.class);
        String flightId = boardingPass.getFlight().getFlightId();
        String passengerId = boardingPass.getPassenger().getPassengerId();
        ArrayList<String[]> resCsvData = Csv.readCsv(Csv.RESERVATION_CSV_PATH);
        String[] reservation = new String[6];
        for (String[] resStr : resCsvData) {
            if (resStr[1].equals(passengerId) && resStr[2].equals(flightId)) {
                reservation = resStr.clone();
            }
        }
        BufferedWriter out;
        ArrayList<String[]> boardingPassData = Csv.readCsv(Csv.BOARDING_PASS_CSV_PATH);
        int tagNo = 0;
        for (String[] boardingPassStr : boardingPassData) {
            if (boardingPassStr[1].equals(flightId)) {
                tagNo++;
            }
        }
        StringBuilder no = new StringBuilder(String.valueOf(tagNo));
        no.append(" ".repeat(Math.max(0, 3 - String.valueOf(tagNo).length())));
        tagData.delete(0, tagData.length());
        if (!reservation[5].equals("0")) {
            tagData.append("NO.").append(no).append("\n")
                    .append("HAND BAGGAGE: ").append(reservation[5]).append("\n\n");
            try {
                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(tagFilePath)));
                out.write("   _____________TAG_____________\n" +
                        " / \\                            \\.\n" +
                        "|   |                           |.\n" +
                        " \\__|                           |.\n" +
                        "    |  NO." + no + "                   |.\n" +
                        "    |  HAND BAGGAGE: " + reservation[5] + "          |.\n" +
                        "    |                           |.\n" +
                        "    |   ________________________|____\n" +
                        "    |  /                            /.\n" +
                        "    \\_/Group58_____________________/.");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(tagFilePath)));
                tagData.append("You don't have hand baggage\n\n");
                out.write("You don't have hand baggage");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!reservation[6].equals("0")) {
            String boardingGate = boardingPass.getFlight().getBoardingGate();
            if (boardingGate.length() == 2) {
                boardingGate = boardingGate + " ";
            }
            tagData.append("NO.").append(no).append("\n")
                    .append("COUNTER: ").append(boardingGate).append("\n")
                    .append("CHECKED BAGGAGE: ").append(reservation[6]).append("\n");
            try {
                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(tagFilePath, true)));
                out.write("\n\n_______________________________________\n\n");
                out.write("   ___________VOUCHER___________\n" +
                        " / \\                            \\.\n" +
                        "|   |                           |.\n" +
                        " \\__|                           |.\n" +
                        "    |  NO." + no + "                   |.\n" +
                        "    |  COUNTER: " + boardingGate + "             |.\n" +
                        "    |  CHECKED BAGGAGE: " + reservation[6] + "       |.\n" +
                        "    |                           |.\n" +
                        "    |   ________________________|____\n" +
                        "    |  /                            /.\n" +
                        "    \\_/Group58_____________________/.");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(tagFilePath)));
                tagData.append("You don't have checked baggage\n");
                out.write("You don't have checked baggage");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private MediaPlayer sound() {
        String path = "src/main/resources/com/app/flight/audio/printer.mp3";
        Media sound = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        return mediaPlayer;
    }

    public Boolean print(ProgressBar progressBar, Label percentage) {
        int percent;
        ThreadUtil.sleep(50);
        MediaPlayer mediaPlayer = sound();
        printBoardingPass(Json.BOARDING_PASS_JSON_PATH, BOARDING_PASS_TXT_PATH);
        printTag(Json.BOARDING_PASS_JSON_PATH, TAG_TXT_PATH);
        GetReservationImpl getReservation = new GetReservationImpl();
        if (getReservation.updateCheckedFlag()) {
            System.out.println("更新flag成功");
        }
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
