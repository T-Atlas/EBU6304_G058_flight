package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetReservation;
import com.app.flight.service.GetSeatMap;
import com.app.flight.service.impl.GetReservationImpl;
import com.app.flight.service.impl.GetSeatMapImpl;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author HuangHong
 */
public class RetrieveController {
    public ArrayList<Reservation> rList;
    GetReservation getReservation = new GetReservationImpl();
    GetSeatMap getSeatMap = new GetSeatMapImpl();
    @FXML
    private Button next;
    @FXML
    private TableView<Reservation> tableView;
    private IntegerProperty fontSize = new SimpleIntegerProperty((int) Font.getDefault().getSize());
    private StringProperty style = new SimpleStringProperty();


    public void showRetrieve() {
        ObservableList<Reservation> list2 = FXCollections.observableArrayList();
        rList = getReservation.lookupReservations("220802200005217774");
        tableView.isEditable();

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
        check.setPrefWidth(36);
        flightId.setPrefWidth(170);
        departure.setPrefWidth(170);
        destination.setPrefWidth(170);
        time.setPrefWidth(270);
        handBaggage.setPrefWidth(117);
        checkedBaggage.setPrefWidth(117);

        //the style of font size
        check.setStyle("-fx-font-size:15px;-fx-alignment: center");
        flightId.setStyle("-fx-font-size:20px;-fx-alignment: center");
        departure.setStyle("-fx-font-size:20px;-fx-alignment: center");
        destination.setStyle("-fx-font-size:20px;-fx-alignment: center");
        time.setStyle("-fx-font-size:20px;-fx-alignment: center");
        handBaggage.setStyle("-fx-font-size:20px;-fx-alignment: center;");
        checkedBaggage.setStyle("-fx-font-size:20px;-fx-alignment: center");
        baggage.setStyle("-fx-font-size:20px");

        for (int i = 0; i < list2.size(); i++) {
            check.setCellFactory(new Callback<TableColumn<Reservation, Boolean>, TableCell<Reservation, Boolean>>() {
                @Override
                public TableCell<Reservation, Boolean> call(TableColumn<Reservation, Boolean> param) {
                    //make checkbox editable
                    CheckBoxTableCell<Reservation, Boolean> cell = new CheckBoxTableCell<Reservation, Boolean>();
                    return cell;
                }

            });
            flightId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getFlightId()));
            departure.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getDeparture()));
            destination.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getDestination()));
            time.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getFlight().getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            handBaggage.setCellValueFactory(new PropertyValueFactory<Reservation, Number>("handBaggageNum"));
            checkedBaggage.setCellValueFactory(new PropertyValueFactory<Reservation, Number>("checkedBaggageNum"));
        }

        tableView.getColumns().add(check);
        tableView.getColumns().add(flightId);
        tableView.getColumns().add(departure);
        tableView.getColumns().add(destination);
        tableView.getColumns().add(time);
        tableView.getColumns().add(baggage);

    }

    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new SelectSeatController().start(new Stage(), getSeatMap.lookupSeatMap("MU1122"));//need to be change
                ((Stage) (next.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/Retrieve.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        RetrieveController retrieveController = fxmlLoader.getController();
        retrieveController.showRetrieve();
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();

    }

    public void mouseClick(MouseEvent mouseEvent) {
        Reservation selectedRow = tableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedRow.getFlight().getFlightId());
    }
}
