module com.app.fight {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens com.app.flight to javafx.fxml;
    exports com.app.flight;
    exports com.app.flight.controller;
    opens com.app.flight.controller to javafx.fxml;
}