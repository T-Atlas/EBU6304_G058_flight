package com.app.flight.controller;

import com.app.flight.entity.Passenger;
import com.app.flight.service.GetFoodType;
import com.app.flight.service.impl.GetFoodImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author LianJunhong
 */
public class InputNumberController {
    @FXML
    public TextField id;
    GetFoodType getFoodType = new GetFoodImpl();

    public void submit(ActionEvent actionEvent) {
        Passenger p = getFoodType.lookupPassenger(id.getText());
        System.out.println(p);
    }
}
