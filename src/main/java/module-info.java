module com.app.flight {
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
    opens com.app.flight.entity to fastjson,lombok;
    exports com.app.flight.service;
    opens com.app.flight.service to javafx.fxml;
    exports com.app.flight.service.impl;
    opens com.app.flight.service.impl to javafx.fxml;
    exports com.app.flight.service.temp;
    opens com.app.flight.service.temp to javafx.fxml;
}