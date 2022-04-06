package com.app.flight.controller;

import com.app.flight.entity.Passenger;
import com.app.flight.service.GetPassenger;
import com.app.flight.service.impl.GetPassengerImpl;
import com.app.flight.service.temp.GetPassengerImplTemp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author LianJunhong
 */
public class InputNumberController {
    @FXML
    public TextField id;
    GetPassenger getPassenger = new GetPassengerImplTemp();
    GetPassenger getPassenger1 = new GetPassengerImpl();

    public void submit(ActionEvent actionEvent) {
        Passenger p = getPassenger.lookupPassenger(id.getText());
        System.out.println(p);
    }
}
