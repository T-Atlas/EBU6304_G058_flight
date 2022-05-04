package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.service.GetNavigationPicture;
import com.app.flight.service.impl.NavigationPictureImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class NavigationMapController {
    GetNavigationPicture navigationPicture = new NavigationPictureImpl();
    public ImageView map;
    public Button back;

    public void setMap(GetNavigationPicture.gateType gateType) {
        map.setImage(navigationPicture.getNavigationPicture(gateType));
    }

    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/NavigationMap.fxml"));
    }

    public void returnButton(ActionEvent actionEvent) {
    }
}
