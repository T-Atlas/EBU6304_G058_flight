package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetFlight;
import com.app.flight.service.GetReservation;
import com.app.flight.service.GetSeatMap;
import com.app.flight.service.impl.GetFlightImpl;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.service.impl.GetSeatMapImpl;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author HuangHong
 * @version 2.1
 */
public class RetrieveController {
    public ArrayList<Reservation> rList;
    GetReservation getReservation = new GetReservationImpl();
    GetSeatMap getSeatMap = new GetSeatMapImpl();
    @FXML
    private Button next;
    @FXML
    private TableView<Reservation> tableView;

    GetFlight getFlight = new GetFlightImpl();


    public void showRetrieve(Passenger p) {
        ObservableList<Reservation> list2 = FXCollections.observableArrayList();
        rList = getReservation.lookupReservations(p.getPassengerId());
        tableView.setEditable(false);

        if (rList == null) {
            Platform.runLater(() -> {
                try {
                    new ComingSoonController().start(new Stage());
                    ((Stage) (next.getScene().getWindow())).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            for (int i = 0; i < rList.size(); i++) {
                list2.add(i, rList.get(i));
            }
            tableView.setItems(list2);

            TableColumn check = new TableColumn();
            TableColumn flightId = new TableColumn("FlightID");
            TableColumn departure = new TableColumn("Departure");
            TableColumn destination = new TableColumn("Destination");
            TableColumn time = new TableColumn("DepartureTime");
            TableColumn handBaggage = new TableColumn("Carry-on");
            TableColumn checkedBaggage = new TableColumn("Check-in");

            //Combine the two types of baggage into one column
            TableColumn<Reservation, Object> baggage = new TableColumn<Reservation, Object>("Baggage");
            baggage.getColumns().add(handBaggage);
            baggage.getColumns().add(checkedBaggage);

            //Set the size of column
            flightId.setMinWidth(170);
            departure.setMinWidth(194);
            destination.setMinWidth(194);
            time.setMinWidth(270);
            handBaggage.setMinWidth(120);
            checkedBaggage.setMinWidth(120);

            //the style of font size
            check.setStyle("-fx-font-size:15px;-fx-alignment: center");
            flightId.setStyle("-fx-font-size:20px;-fx-alignment: center");
            departure.setStyle("-fx-font-size:20px;-fx-alignment: center");
            destination.setStyle("-fx-font-size:20px;-fx-alignment: center");
            time.setStyle("-fx-font-size:20px;-fx-alignment: center");
            handBaggage.setStyle("-fx-font-size:20px;-fx-alignment: center;");
            checkedBaggage.setStyle("-fx-font-size:20px;-fx-alignment: center");
            baggage.setStyle("-fx-font-size:20px");

            //set the columns can't sortable
            check.setSortable(false);
            flightId.setSortable(false);
            departure.setSortable(false);
            destination.setSortable(false);
            time.setSortable(false);
            handBaggage.setSortable(false);
            checkedBaggage.setSortable(false);

            for (int i = 0; i < list2.size(); i++) {
                flightId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getFlightId()));
                departure.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getDeparture()));
                destination.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getDestination()));
                time.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
                handBaggage.setCellValueFactory(new PropertyValueFactory<Reservation, Number>("handBaggageNum"));
                checkedBaggage.setCellValueFactory(new PropertyValueFactory<Reservation, Number>("checkedBaggageNum"));
            }

            tableView.getColumns().add(flightId);
            tableView.getColumns().add(departure);
            tableView.getColumns().add(destination);
            tableView.getColumns().add(time);
            tableView.getColumns().add(baggage);
        }


    }

    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                Reservation selectedRow = tableView.getSelectionModel().getSelectedItem();
                if (selectedRow != null) {
                    String flightId = selectedRow.getFlight().getFlightId();
                    FXMLLoader fxmlLoader = new SelectSeatController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    stage.setTitle("Please Select Your Seat");
                    Map<Integer, Map<String, Boolean>> seatMap = getSeatMap.lookupSeatMap(flightId);
                    SelectSeatController selectSeatController = fxmlLoader.getController();
                    selectSeatController.flightId = flightId;
                    selectSeatController.showSeatMap(seatMap, selectSeatController);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void start(Stage stage, Passenger pRetrieve) throws IOException {

        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        RetrieveController retrieveController = fxmlLoader.getController();
        retrieveController.showRetrieve(pRetrieve);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();

    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/Retrieve.fxml"));
    }

    public void mouseClick(MouseEvent mouseEvent) {
        Reservation selectedRow = tableView.getSelectionModel().getSelectedItem();
        if (selectedRow != null) {
            getFlight.lookupFlight(selectedRow.getFlight().getFlightId());
        }

    }
}
