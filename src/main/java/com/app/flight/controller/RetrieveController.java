package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Reservation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author HuangHong
 */
public class RetrieveController {
    @FXML
    private Button next;
    @FXML
    private TableView<Reservation> tableView;
    public static Reservation r;



    public void showRetrieve(ActionEvent actionEvent){
        ObservableList<Reservation> ls2 = FXCollections.observableArrayList();

        //ls2.add();
        tableView.setItems(ls2);
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
