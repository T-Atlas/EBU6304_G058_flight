package com.app.flight.service.external;

import com.app.flight.controller.ScanInstructionController;
import javafx.stage.Stage;

import java.util.Scanner;

public class ConsoleListener extends Thread {

    public Stage stage;

    public ScanInstructionController scanInstructionController;

    public ConsoleListener(Stage stage, ScanInstructionController scanInstructionController) {
        this.stage = stage;
        this.scanInstructionController = scanInstructionController;
    }

    @Override
    public void run() {
        Scanner consoleScanner = new Scanner(System.in);
        String idNumber;
        idNumber = consoleScanner.nextLine();
        this.scanInstructionController.checkIdNumber(idNumber);
    }
}
