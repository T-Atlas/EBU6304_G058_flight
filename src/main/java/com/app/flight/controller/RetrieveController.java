package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetFlight;
import com.app.flight.service.GetReservation;
import com.app.flight.service.GetSeatMap;
import com.app.flight.service.impl.GetFlightImpl;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.service.impl.SeatMapImpl;
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
    public Button help;
    GetReservation getReservation = new GetReservationImpl();
    GetSeatMap getSeatMap = new SeatMapImpl();
    GetFlight getFlight = new GetFlightImpl();
    @FXML
    private Button next;
    @FXML
    private TableView<Reservation> tableView;

    private Reservation preSelectedRow;

    public void showRetrieve(Passenger p) {
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        rList = getReservation.lookupReservations(p.getPassengerId());
        tableView.setEditable(false);
        next.setDisable(true);

        if (rList == null) {
            Platform.runLater(() -> {
                try {
                    Stage stage = (Stage) next.getScene().getWindow();
                    FXMLLoader fxmlLoader = new ComingSoonController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            for (int i = 0; i < rList.size(); i++) {
                list.add(i, rList.get(i));
            }
            tableView.setItems(list);

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
            flightId.setResizable(false);
            flightId.setPrefWidth(163);
            flightId.setReorderable(false);
            departure.setResizable(false);
            departure.setPrefWidth(190);
            departure.setReorderable(false);
            destination.setResizable(false);
            destination.setPrefWidth(190);
            destination.setReorderable(false);
            time.setResizable(false);
            time.setPrefWidth(270);
            time.setReorderable(false);
            handBaggage.setResizable(false);
            handBaggage.setPrefWidth(120);
            handBaggage.setReorderable(false);
            checkedBaggage.setResizable(false);
            checkedBaggage.setPrefWidth(120);
            checkedBaggage.setReorderable(false);

            //the style of font size
            flightId.setStyle("-fx-font-size:20px;-fx-alignment: center");
            departure.setStyle("-fx-font-size:20px;-fx-alignment: center");
            destination.setStyle("-fx-font-size:20px;-fx-alignment: center");
            time.setStyle("-fx-font-size:20px;-fx-alignment: center");
            handBaggage.setStyle("-fx-font-size:20px;-fx-alignment: center;");
            checkedBaggage.setStyle("-fx-font-size:20px;-fx-alignment: center");
            baggage.setStyle("-fx-font-size:20px;-fx-alignment: center");

            //set the columns can't sortable
            flightId.setSortable(false);
            departure.setSortable(false);
            destination.setSortable(false);
            time.setSortable(false);
            handBaggage.setSortable(false);
            checkedBaggage.setSortable(false);

            for (Reservation reservation : list) {
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
                    Map<Integer, Map<String, Boolean>> seatMap = getSeatMap.lookupSeatMap(flightId);
                    SelectSeatController selectSeatController = fxmlLoader.getController();
                    selectSeatController.flightId = flightId;
                    selectSeatController.showSeatMap(seatMap);
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
            if (selectedRow != preSelectedRow) {
                getFlight.lookupFlight(selectedRow.getFlight().getFlightId());
            }
            next.setDisable(false);
        } else {
            next.setDisable(true);
        }
        preSelectedRow = selectedRow;
    }

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
}
