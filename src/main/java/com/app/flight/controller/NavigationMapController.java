package com.app.flight.controller;

import com.app.flight.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class NavigationMapController {
    public ImageView map;
    public Button back;

    public void setMap(ImageView map) {
    }

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/NavigationMap.fxml"));
    }

    public void returnButton(ActionEvent actionEvent) {
    }
}
