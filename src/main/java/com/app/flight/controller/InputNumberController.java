package com.app.flight.controller;

import com.app.flight.entity.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author LianJunhong
 */
public class InputNumberController {
    @FXML
    public TextField id;
    getFoodtype getFoodtype1= new getfoodimpl();

    public void submit(ActionEvent actionEvent) {
        Passenger p = getFoodtype1.lookupPassenger(id.getText());
        System.out.println(p);
    }
}
