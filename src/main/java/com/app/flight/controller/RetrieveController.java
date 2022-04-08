package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetReservation;
import com.app.flight.service.temp.GetPassengerImplTemp;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author HuangHong
 */
public class RetrieveController {
    @FXML
    private Button next;
    @FXML
    private TableView<Reservation> tableView;
    public ArrayList<Reservation> rList;
    GetReservation getReservation = new GetPassengerImplTemp();


    public void showRetrieve(ActionEvent actionEvent){
        ObservableList<Reservation> list2 = FXCollections.observableArrayList();
        rList = getReservation.lookupReservations("220802200005217777");

        for(int i = 0; i < rList.size(); i++){
            list2.add(i,rList.get(i));
        }
        tableView.setItems(list2);

        TableColumn flightId= new TableColumn("FlightID");
        TableColumn departure=new TableColumn("Departure");
        TableColumn destination=new TableColumn("Destination");
        TableColumn time=new TableColumn("Time");
        TableColumn handBaggage=new TableColumn("Carry-on");
        TableColumn checkedBaggage=new TableColumn("Check-in");

        //Combine the two types of baggage into one column
        TableColumn<Reservation, Object> baggage=new TableColumn<Reservation, Object>("Baggage");
        baggage.getColumns().add(handBaggage);
        baggage.getColumns().add(checkedBaggage);

        for(int i = 0; i < list2.size(); i++){
            //flightId.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight"));
            /*flightId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> r) {
                    // p.getValue() returns the Person instance for a particular TableView row
                    return r.getValue().getFlight().getFlightId();
                }
            });*/

            //departure.setCellValueFactory(new PropertyValueFactory<Reservation, String>(list2.get(i).getFlight().getDeparture()));
            //destination.setCellValueFactory(new PropertyValueFactory<Reservation, String>(list2.get(i).getFlight().getDestination()));
            //time.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDateTime>(String.valueOf(list2.get(i).getFlight().getDepartureTime())));
            handBaggage.setCellValueFactory(new PropertyValueFactory<Reservation, Number>("handBaggageNum"));
            checkedBaggage.setCellValueFactory(new PropertyValueFactory<Reservation, Number>("checkedBaggageNum"));
        }

        tableView.getColumns().add(flightId);
        tableView.getColumns().add(departure);
        tableView.getColumns().add(destination);
        tableView.getColumns().add(time);
        tableView.getColumns().add(baggage);

    }

    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new FoodTypeController().start(new Stage());//need to be change
                ((Stage) (next.getScene().getWindow())).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/Retrieve.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();

    }
}
