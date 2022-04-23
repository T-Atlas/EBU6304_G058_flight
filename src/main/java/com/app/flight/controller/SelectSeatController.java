package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.service.SetSeatMap;
import com.app.flight.service.impl.SetSeatMapImpl;
import com.app.flight.util.DataParser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhenghan
 * @version 2.1
 */
public class SelectSeatController {

    @FXML
    public ChoiceBox<String> seat;

    @FXML
    public Button help;

    @FXML
    public Button next;

    @FXML
    public GridPane gridPane;

    public Button choiceButton;

    public int choiceRow;

    public String choiceColumn;

    public String flightId;

    @FXML
    public void nextClick(ActionEvent actionEvent) {
        if (this.choiceColumn != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Selected Seat: " + this.choiceRow + this.choiceColumn);
            alert.setContentText("Please continue your check-in");
            alert.showAndWait();
            Platform.runLater(() -> {
                Stage stage = (Stage) next.getScene().getWindow();
                try {
                    SetSeatMap setSeatMap = new SetSeatMapImpl();
                    setSeatMap.updateSeatMap(flightId, choiceColumn, choiceRow);
                    FXMLLoader fxmlLoader = new FoodTypeController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @FXML
    public void helpClick(ActionEvent actionEvent) {
//        gridPane.getRowConstraints().add(new RowConstraints(70, 70, 70));
//        Text rowNo = new Text(String.valueOf(r));
//        gridPane.add(rowNo, 0, r);
//        GridPane.setMargin(rowNo, new Insets(24));
//        int[] columnNo = {1, 2, 3, 5, 6, 7};
//        for(int c: columnNo) {
//            Button button = new Button("1D");
//            button.setMinWidth(80);
//            button.setStyle("-fx-font-size: 25;");
//            button.setStyle("-fx-background-color: LIGHTGREY");
//            gridPane.add(button, c, r);
//            GridPane.setMargin(button, new Insets(18));
//        }
//        r++;
    }

    /**
     * The code for other pages to open SelectSeat.fxml
     */
    public void start(Stage stage, Map<Integer, Map<String, Boolean>> seatMap, String flightId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectSeat.fxml"));
        SelectSeatController selectSeatController = fxmlLoader.getController();
        selectSeatController.flightId = flightId;
        selectSeatController.showSeatMap(seatMap, selectSeatController);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Please Select Your Seat");

        stage.setScene(scene);
        stage.show();
    }

    public void showSeatMap(Map<Integer, Map<String, Boolean>> seatMap, SelectSeatController selectSeatController) {

        for (Map.Entry<Integer, Map<String, Boolean>> rowMap : seatMap.entrySet()) {
            selectSeatController.gridPane.getRowConstraints().add(new RowConstraints(70, 70, 70));

            Text rowText = new Text(String.valueOf(rowMap.getKey()));
            selectSeatController.gridPane.add(rowText, 0, rowMap.getKey() - 1);
            GridPane.setMargin(rowText, new Insets(24));
            for (Map.Entry<String, Boolean> seats : rowMap.getValue().entrySet()) {
                Button button = new Button(rowMap.getKey() + seats.getKey());
                button.setMinWidth(80);
                if (seats.getValue()) {
                    button.setStyle("-fx-background-color: #81cbf5");
                    selectSeatController.choiceButton = button;
                    button.setOnAction(actionEvent -> {
                        selectSeatController.choiceButton.setStyle("-fx-background-color: #81cbf5");

                        selectSeatController.choiceRow = rowMap.getKey();
                        selectSeatController.choiceColumn = String.valueOf(seats.getKey());
                        selectSeatController.choiceButton = button;
                        button.setStyle("-fx-background-color: #008ef3");
                    });
                } else {
                    button.setStyle("-fx-background-color: #a8a8a8");
                    button.setOnAction(actionEvent -> {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Sorry");
                        alert.setHeaderText("You have selected seat: " + rowMap.getKey() + seats.getKey());
                        alert.setContentText("The seat is occupied. Please select another seat.");
                        alert.showAndWait();
                    });
                }
                selectSeatController.gridPane.add(button, DataParser.stringToNo(seats.getKey()), rowMap.getKey() - 1);
                GridPane.setMargin(button, new Insets(18));
            }
        }
    }

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/SelectSeat.fxml"));
    }
}
