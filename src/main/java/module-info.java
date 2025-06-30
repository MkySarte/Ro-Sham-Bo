module id.mky.roshambo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens id.mky.roshambo to javafx.fxml;
    exports id.mky.roshambo;
    exports id.mky.roshambo.alt;
    opens id.mky.roshambo.alt to javafx.fxml;
}