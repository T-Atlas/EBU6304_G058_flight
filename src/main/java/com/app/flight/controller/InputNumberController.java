package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import com.app.flight.service.temp.GetPassengerImplTemp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LianJunhong
 */
public class InputNumberController {
    @FXML
    public TextField id;
    @FXML
    public Label annotation;
    GetPassenger getPassenger = new GetPassengerImplTemp();
    GetPassenger getPassenger1 = new GetPassengerImpl();

    public void submit(ActionEvent actionEvent) {
        Passenger p = getPassenger.lookupPassenger(id.getText());
        System.out.println(p);
    }

    /**
     * The code for other pages to open InputNumber.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/InputNumber.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    //TODO:BUG
    public void labelText(String type) {
        if ("booking".equals(type)) {
            annotation.setText("--&gt; Please input your booking number:");
        }else if ("id".equals(type)) {
            annotation.setText("--&gt; Please input your ID card number:");
        }
    }
}
