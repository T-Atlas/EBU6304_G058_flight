module com.app.fight {
    requires javafx.controls;
    requires javafx.fxml;
    requires fastjson;
    requires javacsv;
    requires lombok;
    requires hutool.all;

    opens com.app.flight to javafx.fxml;
    exports com.app.flight;
    exports com.app.flight.controller;
    opens com.app.flight.controller to javafx.fxml;
    exports com.app.flight.util;
    opens com.app.flight.util to javafx.fxml;
    exports com.app.flight.entity;
    opens com.app.flight.entity to fastjson;
}