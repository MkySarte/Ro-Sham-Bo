module com.example.roshambo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.roshambo to javafx.fxml;
    exports com.example.roshambo;
}