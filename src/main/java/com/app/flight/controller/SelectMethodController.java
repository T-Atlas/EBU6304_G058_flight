package com.app.flight.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.app.flight.Main;
import com.app.flight.service.external.Scanner;
import com.app.flight.util.Common;
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
 * @author LianJunhong
 * @version 2.1
 */
public class SelectMethodController {

    @FXML
    public RadioButton idNum;
    @FXML
    public RadioButton scan;
    @FXML
    public RadioButton bookingNum;
    @FXML
    public ToggleGroup method;
    public Button next;
    public Button help;

    /**
     * The code for other pages to open SelectMethod.fxml.
     *
     * @param stage
     * @throws IOException
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The method is used to check whether the user has selected the method.
     *
     * @param actionEvent
     */
    public void check(ActionEvent actionEvent) {
        //if radiobutton in toggle-group is selected,make next button enable
        if (method.getSelectedToggle() != null) {
            next.setDisable(false);
        }
    }

    /**
     * The method is used to judge which method is selected and open the next page.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void nextClick(ActionEvent actionEvent) {

        idNum.setUserData("id");
        bookingNum.setUserData("booking");
        scan.setUserData("scan");

        Common.setSelectType((String) method.getSelectedToggle().getUserData());
        Stage stage = (Stage) next.getScene().getWindow();
        //send selected method to next controller depending on which radiobutton is selected
        if (bookingNum.isSelected() || idNum.isSelected()) {
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new InputNumberController().getLoader();//Controller that needs to be modified for page display
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    InputNumberController inputNumberController = fxmlLoader.getController();
                    inputNumberController.type = (String) method.getSelectedToggle().getUserData();
                    inputNumberController.next.setDisable(true);
                    if (inputNumberController.type.equals("id")) {
                        inputNumberController.number.textProperty().addListener(changeListener -> {
                            inputNumberController.next.setDisable((inputNumberController.number.getText().length() <= 0) || (inputNumberController.surName.getText().length() <= 0));
                        });
                        inputNumberController.surName.textProperty().addListener(changeListener -> {
                            inputNumberController.next.setDisable((inputNumberController.number.getText().length() <= 0) || (inputNumberController.surName.getText().length() <= 0));
                        });
                        inputNumberController.annotation.setText("--> Please input your ID number and surname:");
                        inputNumberController.numLabel.setText("ID Number:");
                        inputNumberController.nameLabel.setText("Surname:");

                    } else if (inputNumberController.type.equals("booking")) {
                        inputNumberController.number.textProperty().addListener(changeListener -> {
                            inputNumberController.next.setDisable(inputNumberController.number.getText().length() <= 0);
                        });
                        inputNumberController.annotation.setText("--> Please input your booking number:");
                        inputNumberController.nameClean.setVisible(false);
                        inputNumberController.nameClean.setVisible(false);
                        inputNumberController.surName.setVisible(false);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Scanner scanner = new Scanner();
            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new ScanInstructionController().getLoader();
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                    ScanInstructionController scanInstructionController = fxmlLoader.getController();
                    scanInstructionController.mediaView.setMediaPlayer(scanner.playVideo());
                    scanner.ConsoleScanner(scanInstructionController, stage);
                    ThreadUtil.execute(scanner);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * This method is used to get the loader for the SelectMethod controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/SelectMethod.fxml"));
    }

    /**
     * This method is used to get the help page.
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void helpClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new HelpController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                HelpController helpController = fxmlLoader.getController();
                helpController.setControllerName(this.getClass().getSimpleName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
