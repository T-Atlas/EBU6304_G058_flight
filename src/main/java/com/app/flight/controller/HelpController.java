package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 */
public class HelpController {
    public Button back;
    public Button call;

    private String controllerName;

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/Help.fxml"));
    }

    public void callButton(ActionEvent actionEvent) {
        //A pop-up window appears after the button is pressed, and the progress bar counts down to five seconds and then displays Please be patient.
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.FINISH);
        alert.setTitle("Success");
        alert.setHeaderText("Please be patient.");
        alert.showAndWait();
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO:Unfinished
        Platform.runLater(() -> {
            Stage stage = (Stage) back.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = null;
                switch (controllerName) {
                    case "FoodTypeController" -> {
                        fxmlLoader = new FoodTypeController().getLoader();
                    }
                    case "InfoConfirmController" -> {
                        fxmlLoader = new InfoConfirmController().getLoader();
                    }
                    case "SelectMethodController" -> {
                        fxmlLoader = new SelectMethodController().getLoader();
                    }
                    case "SelectSeatController" -> {
                        fxmlLoader = new SelectSeatController().getLoader();
                    }
                    case "InputNumberController" -> {
                        fxmlLoader = new InputNumberController().getLoader();
                    }
                    case "SelectPaymentController" -> {
                        fxmlLoader = new SelectPaymentController().getLoader();
                    }
                    case "ScanInstructionController" -> {
                        fxmlLoader = new ScanInstructionController().getLoader();
                    }
                    case "RetrieveController" -> {
                        fxmlLoader = new RetrieveController().getLoader();
                    }
                    case "ResultController" -> {
                        fxmlLoader = new ResultController().getLoader();
                    }
                    case "PrintTagsController" -> {
                        fxmlLoader = new PrintTagsController().getLoader();
                    }
                    default -> fxmlLoader = new ComingSoonController().getLoader();
                }
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
