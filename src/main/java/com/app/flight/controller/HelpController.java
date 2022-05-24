package com.app.flight.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.service.external.Scanner;
import com.app.flight.service.impl.GetFlightImpl;
import com.app.flight.service.impl.GetPassengerImpl;
import com.app.flight.service.impl.SeatMapImpl;
import com.app.flight.util.Obj;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


/**
 * @author LianJunhong
 */
public class HelpController {
    @FXML
    private Button back;
    @FXML
    private Button call;

    private String controllerName;


    /**
     * This setter is used to set the controller name.
     *
     * @param controllerName the controller name
     */
    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    /**
     * Call this method will start help page.
     *
     * @param stage the stage to show the page
     * @throws IOException the exception
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to get the loader.
     *
     * @return the FXMLLoader
     * @throws IOException the IO exception
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/Help.fxml"));
    }

    /**
     * This method is used to handle the call help button.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void callButton(ActionEvent actionEvent) {
        //A pop-up window appears after the button is pressed, and the progress bar counts down to five seconds and then displays Please be patient.
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.FINISH);
        alert.setTitle("Success");
        alert.setHeaderText("Please be patient.");
        alert.showAndWait();
    }

    /**
     * This method is used to handle the back button.
     * The method will determine which controller to go back to.
     * And will keep the previous page data.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void backButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) back.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = null;
                switch (controllerName) {
                    case "FoodTypeController" -> {
                        fxmlLoader = new FoodTypeController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    }
                    case "InfoConfirmController" -> {
                        Passenger p = GetPassengerImpl.lookupPassenger();
                        if (p != null) {
                            fxmlLoader = new InfoConfirmController().getLoader();
                        } else {
                            fxmlLoader = new ComingSoonController().getLoader();
                        }
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        if (p != null) {
                            InfoConfirmController i = fxmlLoader.getController();
                            i.showNum(p);
                            i.passengerRetrieve = p;
                        }
                    }
                    case "SelectMethodController" -> {
                        fxmlLoader = new SelectMethodController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    }
                    case "SelectPaymentController" -> {
                        fxmlLoader = new SelectPaymentController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    }
                    case "SelectSeatController" -> {
                        fxmlLoader = new SelectSeatController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        String flightId = Objects.requireNonNull(GetFlightImpl.lookupFlight()).getFlightId();
                        stage.setTitle("Please Select Your SeatUtil");
                        SeatMapImpl getSeatMap = new SeatMapImpl();
                        Map<Integer, Map<String, Boolean>> seatMap = getSeatMap.lookupSeatMap(flightId);
                        SelectSeatController selectSeatController = fxmlLoader.getController();
                        selectSeatController.flightId = flightId;
                        selectSeatController.showSeatMap(seatMap);
                    }
                    case "InputNumberController" -> {
                        fxmlLoader = new InputNumberController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        InputNumberController inputNumberController = fxmlLoader.getController();
                        inputNumberController.type = Obj.getSelectType();
                        inputNumberController.next.setDisable(true);
                        inputNumberController.number.textProperty().addListener(changeListener -> {
                            inputNumberController.next.setDisable(inputNumberController.number.getText().length() <= 0);
                        });
                        if (inputNumberController.type.equals("id")) {
                            inputNumberController.annotation.setText("--> Please input your ID number and surname:");
                            inputNumberController.numLabel.setText("ID Number:");
                            inputNumberController.nameLabel.setText("Surname:");
                        } else if (inputNumberController.type.equals("booking")) {
                            inputNumberController.annotation.setText("--> Please input your booking number:");
                            inputNumberController.nameClean.setVisible(false);
                            inputNumberController.nameClean.setVisible(false);
                            inputNumberController.surName.setVisible(false);
                        }
                    }
                    case "PaymentController" -> {
                        fxmlLoader = new PaymentController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        PaymentController paymentController = fxmlLoader.getController();
                        paymentController.pay(Objects.requireNonNull(Obj.getSelectType()));
                    }
                    case "ScanInstructionController" -> {
                        fxmlLoader = new ScanInstructionController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        Scanner scanner = new Scanner();
                        ScanInstructionController scanInstructionController = fxmlLoader.getController();
                        scanInstructionController.mediaView.setMediaPlayer(scanner.playVideo());
                        scanner.ConsoleScanner(scanInstructionController, stage);
                        ThreadUtil.execute(scanner);
                    }
                    case "RetrieveController" -> {
                        fxmlLoader = new RetrieveController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        RetrieveController retrieveController = fxmlLoader.getController();
                        retrieveController.showRetrieve(Objects.requireNonNull(GetPassengerImpl.lookupPassenger()));
                    }
                    case "ResultController" -> {
                        fxmlLoader = new ResultController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        ResultController resultController = fxmlLoader.getController();
                        resultController.showBoardingPass(false);
                    }
                    case "PrintTagsController" -> {
                        fxmlLoader = new PrintTagsController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                        PrintTagsController printTagsController = fxmlLoader.getController();
                        printTagsController.progressBar.setProgress(1);
                        printTagsController.percentage.setText("Success!");
                        printTagsController.next.setDisable(false);
                        printTagsController.help.setVisible(true);
                    }
                    default -> {
                        fxmlLoader = new ComingSoonController().getLoader();
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
