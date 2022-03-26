module com.app.fight {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.flight to javafx.fxml;
    exports com.app.flight;
    exports com.app.flight.controller;
    opens com.app.flight.controller to javafx.fxml;
}