module com.app.flight {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.alibaba.fastjson2;
    requires javacsv;
    requires lombok;
    requires hutool.all;
    requires javafx.media;
    requires com.google.zxing;

    opens com.app.flight to javafx.fxml, javafx.media, com.alibaba.fastjson2, lombok;
    exports com.app.flight;
    exports com.app.flight.controller;
    opens com.app.flight.controller to javafx.fxml;
    exports com.app.flight.util;
    opens com.app.flight.util to com.alibaba.fastjson2, javafx.fxml;
    exports com.app.flight.entity;
    opens com.app.flight.entity to com.alibaba.fastjson2, lombok;
    exports com.app.flight.service;
    opens com.app.flight.service to javafx.fxml;
    exports com.app.flight.service.impl;
    opens com.app.flight.service.impl to com.alibaba.fastjson2, javafx.fxml;
    exports com.app.flight.service.temp;
    opens com.app.flight.service.temp to javafx.fxml;
}