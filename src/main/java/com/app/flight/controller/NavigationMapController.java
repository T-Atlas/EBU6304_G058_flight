package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.service.GetNavigationPicture;
import com.app.flight.service.impl.NavigationPictureImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationMapController {
    private final GetNavigationPicture navigationPicture = new NavigationPictureImpl();
    @FXML
    private ImageView map;
    @FXML
    private Button back;

    /**
     * This method is called when the user clicks on the map.
     *
     * @param gateName the gate name
     */
    protected void setMap(String gateName) {
        map.setImage(navigationPicture.getNavigationPicture(gateName));
    }

    /**
     * This method is used to get the loader for the NavigationMap controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/NavigationMap.fxml"));
    }

    /**
     * This method is called when the user clicks on the back button.
     * And it allows the user to go back to the previous scene.
     *
     * @param actionEvent the event
     */
    @FXML
    private void returnButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) back.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new ResultController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                ResultController resultController = fxmlLoader.getController();
                resultController.showBoardingPass(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
