package com.app.flight.controller;

import com.app.flight.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class SelectSeatController {

    @FXML
    public ChoiceBox<String> seat;

    @FXML
    public Button help;

    @FXML
    public Button next;

    @FXML
    public void nextClick(ActionEvent actionEvent) {
        String choice = seat.getValue();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Your Selected Seat");
        alert.setContentText(choice);
        alert.showAndWait();
        Platform.runLater(() -> {
            try {
                new FoodTypeController().start(new Stage());
                ((Stage) (next.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void helpClick(ActionEvent actionEvent) {

    }

    /**
     * The code for other pages to open SelectSeat.fxml
     */
    public void start(Stage stage, Map<String, Boolean> seatMap) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectSeat.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Please Select Your Seat");

        SelectSeatController selectSeatController = fxmlLoader.getController();
        for(Map.Entry<String, Boolean> seats: seatMap.entrySet()) {
            if(seats.getValue())
                selectSeatController.seat.getItems().add(seats.getKey());
        }

        stage.setScene(scene);
        stage.show();
    }

}
