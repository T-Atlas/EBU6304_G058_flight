package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.Food;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This code is used to select the food type.
 *
 * @author HuangHong
 * @version 2.1
 * @date 2022.3.31
 */
public class FoodTypeController {

    @FXML
    public ToggleGroup tg;
    @FXML
    private Button next;
    @FXML
    private RadioButton r1;
    @FXML
    private RadioButton r2;
    @FXML
    private RadioButton r3;


    private String type = null;


    /**
     * To judge which food type is the passenger selected
     *
     * @return type
     */
    public String foodType() {

        r1.setUserData(Food.foodType.STANDARD);
        r2.setUserData(Food.foodType.VEGETARIAN);
        r3.setUserData(Food.foodType.HALAL);

        type = tg.getSelectedToggle().getUserData().toString();
        return type;

    }

    /**
     * The code for button "next" in ” SelectFoodType.fxml“
     * When click the button, change to “CheckInResult.fxml”
     */
    public void nextClick(ActionEvent actionEvent) {

        //invoke foodType() method to get the user's choice of food type
        type = foodType();
        System.out.println(type);

        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new ResultController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The code for other pages to open SelectFoodType.fxml
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/SelectFoodType.fxml"));
    }

}
