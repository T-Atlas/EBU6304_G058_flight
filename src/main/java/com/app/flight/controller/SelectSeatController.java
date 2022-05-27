package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Seat;
import com.app.flight.service.SetSeatMap;
import com.app.flight.service.impl.SeatMapImpl;
import com.app.flight.util.Common;
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

    private final int firstClassLimit = Seat.FIRST_CLASS.getRow();
    private final int businessClassLimit = Seat.BUSINESS_CLASS.getRow();
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

    public double choicePrice;

    public String flightId;
    public Button previousChoiceButton;
    public int previousChoiceRow;

    /**
     * The code for button "next" to go to "SelectFood.fxml"
     *
     * @param actionEvent
     * @throws IOException
     */
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
                    SetSeatMap setSeatMap = new SeatMapImpl();
                    setSeatMap.updateSeatMap(flightId, choiceColumn, choiceRow, choicePrice);
                    FXMLLoader fxmlLoader = new FoodTypeController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * The code for button "help" to show the help message
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void helpClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new HelpController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                HelpController helpController = fxmlLoader.getController();
                helpController.setControllerName(this.getClass().getSimpleName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * The code for other pages to open SelectSeat.fxml
     * @param stage
     * @param seatMap
     * @param flightId
     * @throws IOException
     */
    public void start(Stage stage, Map<Integer, Map<String, Boolean>> seatMap, String flightId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/SelectSeat.fxml"));
        SelectSeatController selectSeatController = fxmlLoader.getController();
        selectSeatController.flightId = flightId;
        selectSeatController.showSeatMap(seatMap);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Please Select Your SeatUtil");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The method is used to show the seat map in the table view.
     *
     * @param seatMap
     * @throws IOException
     */
    public void showSeatMap(Map<Integer, Map<String, Boolean>> seatMap) {
        for (Map.Entry<Integer, Map<String, Boolean>> rowMap : seatMap.entrySet()) {
            this.gridPane.getRowConstraints().add(new RowConstraints(70, 70, 70));

            Text rowText = new Text(String.valueOf(rowMap.getKey()));
            this.gridPane.add(rowText, 0, rowMap.getKey() - 1);
            GridPane.setMargin(rowText, new Insets(24));
            for (Map.Entry<String, Boolean> seats : rowMap.getValue().entrySet()) {
                Button button = new Button(rowMap.getKey() + seats.getKey());
                button.setMinWidth(80);
                if (seats.getValue()) {
                    button.setStyle(getSeatButtonColor(rowMap.getKey()));
                    //selectSeatController.choiceButton = button;

                    button.setOnAction(actionEvent -> {
                        this.previousChoiceButton = this.choiceButton;
                        this.choiceButton = button;
                        this.previousChoiceRow = this.choiceRow;
                        this.choiceRow = rowMap.getKey();
                        if (this.previousChoiceButton != null) {
                            this.previousChoiceButton.setStyle(getSeatButtonColor(this.previousChoiceRow));
                        }
                        this.choiceRow = rowMap.getKey();
                        this.choiceColumn = String.valueOf(seats.getKey());
                        this.choiceButton = button;
                        this.choicePrice = getSeatPrice(this.choiceRow);
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
                this.gridPane.add(button, Common.stringToNo(seats.getKey()), rowMap.getKey() - 1);
                GridPane.setMargin(button, new Insets(18));
            }
        }
    }

    /**
     * The method is used to show the different seat color.
     *
     * @param rowNo
     * @return seat color
     */
    public String getSeatButtonColor(int rowNo) {
        String color;
        if (rowNo > 0 && rowNo <= firstClassLimit) {
            // FIRST_CLASS
            color = "#9dd39a";
        } else if (rowNo > firstClassLimit && rowNo <= firstClassLimit + businessClassLimit) {
            // BUSINESS_CLASS
            color = "#e0d995";
        } else {
            // ECONOMY_CLASS
            color = "#81cbf5";
        }
        return "-fx-background-color: " + color;
    }

    /**
     * The method is used to get the seat price.
     *
     * @param rowNo
     * @return seat price
     */
    public double getSeatPrice(int rowNo) {
        if (rowNo > 0 && rowNo <= firstClassLimit) {
            // FIRST_CLASS
            return Seat.FIRST_CLASS.getPrice();
        } else if (rowNo > firstClassLimit && rowNo <= firstClassLimit + businessClassLimit) {
            // BUSINESS_CLASS
            return Seat.BUSINESS_CLASS.getPrice();
        } else {
            // ECONOMY_CLASS
            return Seat.ECONOMY_CLASS.getPrice();
        }
    }

    /**
     * This method is used to get the loader for the SelectSeat controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/SelectSeat.fxml"));
    }
}
