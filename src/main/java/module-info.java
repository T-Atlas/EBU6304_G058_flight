module com.app.fight {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.fight to javafx.fxml;
    exports com.app.fight;
}