module com.app.fight {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.fight to javafx.fxml;
    exports com.app.fight;
    exports com.app.fight.controller;
    opens com.app.fight.controller to javafx.fxml;
}