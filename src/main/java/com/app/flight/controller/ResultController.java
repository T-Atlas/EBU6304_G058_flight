package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.entity.BoardingPass;
import com.app.flight.service.GetBoardingPass;
import com.app.flight.service.impl.GetBoardingPassImpl;
import com.app.flight.util.Csv;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author HuangHong
 * @version 2.1
 */
public class ResultController {

    public Button help;
    public BoardingPass boardingPass;
    public Button detail;
    GetBoardingPass getBoardingPass = new GetBoardingPassImpl();
    @FXML
    private Button next;
    @FXML
    private Label name;
    @FXML
    private Label departure;
    @FXML
    private Label destination;
    @FXML
    private Label boardingGate;
    @FXML
    private Label seat;
    @FXML
    private Label foodType;


    /**
     * This method is used to show the boarding pass from Csv.
     *
     * @param addCsv
     * @throws IOException
     */
    public void showBoardingPass(boolean addCsv) {
        boardingPass = getBoardingPass.lookupBoardingPass();
        name.setText(boardingPass.getPassenger().getFirstName() + boardingPass.getPassenger().getLastName());
        departure.setText(boardingPass.getFlight().getDeparture());
        destination.setText(boardingPass.getFlight().getDestination());
        boardingGate.setText(boardingPass.getFlight().getBoardingGate());
        seat.setText(boardingPass.getSeatNo());
        foodType.setText(String.valueOf(boardingPass.getFood().getFoodName()));
        if (addCsv) {
            Csv.addCsv(boardingPass, Csv.BOARDING_PASS_CSV_PATH, false);
        }
    }

    /**
     * The code for other pages to open CheckInResult.fxml
     *
     * @param stage
     * @throws IOException
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Smart flight check-in kiosk");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The code for button "next" to go to "Payment.fxml"
     * @param actionEvent
     * @throws IOException
     */
    public void nextClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) next.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectPaymentController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * This method is used to get the loader for the Result controller.
     *
     * @return a new FXMLLoader
     */
    public FXMLLoader getLoader() {
        return new FXMLLoader(Main.class.getResource("fxml/CheckInResult.fxml"));
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

    /**
     * This method is used to show the boarding gate map by the button.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void showMap(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Stage stage = (Stage) help.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new NavigationMapController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
                NavigationMapController navigationMapController = fxmlLoader.getController();
                String gateName = boardingPass.getFlight().getBoardingGate();
                navigationMapController.setMap(gateName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
