package com.app.flight.controller;

import com.app.flight.Main;
import com.app.flight.service.Admin;
import com.app.flight.service.impl.AdminImpl;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The controller for admin viewing the checking information
 *
 * @author Zheng Han
 * @version 2.1
 */
public class AdminViewController {
    @FXML
    public TableColumn<TableRow, String> nameCol;
    @FXML
    public TableColumn<TableRow, String> handBaggageNumCol;
    @FXML
    public TableColumn<TableRow, String> checkedBaggageNumCol;
    @FXML
    public TableColumn<TableRow, String> checkedCol;
    @FXML
    public TableColumn<TableRow, String> seatNoCol;
    @FXML
    public TableColumn<TableRow, String> mealCol;
    @FXML
    public ComboBox<String> comboBox;
    @FXML
    private TableView<TableRow> tableView;
    @FXML
    private Label welcomeMeg;
    @FXML
    private Button logoutButton;


    /**
     * To display message for different admins
     *
     * @param meg the displayed message
     */
    public void setWelcomeMeg(String meg) {
        welcomeMeg.setText(meg);
    }

    /**
     * To search data by flightID when new selections inputs
     *
     * @param flightID the select item of comboBox as input
     */
    public void onFlightSelect(String flightID) {
        Admin admin = new AdminImpl();
        ArrayList<String[]> flightData = admin.searchCheckedInfoByFlightId(flightID);
        ObservableList<TableRow> observableList = FXCollections.observableArrayList();
        tableView.setItems(observableList);
        for (String[] dataRow : flightData) {
            observableList.add(
                    new TableRow(dataRow[0], dataRow[1], dataRow[2], dataRow[3], dataRow[4], dataRow[5])
            );
        }
    }

    /**
     * To associate the tableview columns with inner class, TableRow's attributes
     */
    public void initializeTableView() {
        nameCol.setCellValueFactory(features -> features.getValue().nameProperty());
        handBaggageNumCol.setCellValueFactory(features -> features.getValue().handBaggageNumProperty());
        checkedBaggageNumCol.setCellValueFactory(features -> features.getValue().checkedBaggageNumProperty());
        checkedCol.setCellValueFactory(features -> features.getValue().checkedProperty());
        seatNoCol.setCellValueFactory(features -> features.getValue().seatNoProperty());
        mealCol.setCellValueFactory(features -> features.getValue().mealProperty());
    }

    /**
     * The code to close current page and open the first page
     */
    public void logoutButtonClick() {
        Platform.runLater(() -> {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new SelectLanguageController().getLoader();
                stage.setScene(new Scene(fxmlLoader.load(), 1200, 800));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void start(Stage stage, String meg) throws IOException {
        FXMLLoader fxmlLoader = getLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle(meg);
        AdminViewController adminViewController = fxmlLoader.getController();
        adminViewController.setWelcomeMeg(meg);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * To be called by external controllers to open this page
     *
     * @return the fxml loader of the page AdminView
     * @throws IOException IOException
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(Main.class.getResource("fxml/AdminView.fxml"));
    }

    /**
     * An inner class used as data model of tableView
     */
    public static class TableRow {
        public StringProperty name;
        public StringProperty handBaggageNum;
        public StringProperty checkedBaggageNum;
        public StringProperty checked;
        public StringProperty seatNo;
        public StringProperty meal;

        public TableRow(String name, String handBaggageNum, String checkedBaggageNum, String checked, String seatNo, String meal) {
            this.name = new SimpleStringProperty(name);
            this.handBaggageNum = new SimpleStringProperty(handBaggageNum);
            this.checkedBaggageNum = new SimpleStringProperty(checkedBaggageNum);
            this.checked = new SimpleStringProperty(checked);
            this.seatNo = new SimpleStringProperty(seatNo);
            this.meal = new SimpleStringProperty(meal);
        }

        public StringProperty nameProperty() {
            return name;
        }

        public StringProperty handBaggageNumProperty() {
            return handBaggageNum;
        }

        public StringProperty checkedBaggageNumProperty() {
            return checkedBaggageNum;
        }

        public StringProperty checkedProperty() {
            return checked;
        }

        public StringProperty seatNoProperty() {
            return seatNo;
        }

        public StringProperty mealProperty() {
            return meal;
        }
    }
}
