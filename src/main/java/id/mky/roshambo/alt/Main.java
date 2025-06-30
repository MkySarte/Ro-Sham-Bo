package id.mky.roshambo.alt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
       // ImageIcon icon = new ImageIcon(Main.class.getResource("com/example/roshambo/character/schere.png"));

        // Schriftart vor dem laden laden..xD
        Font.loadFont(getClass().getResourceAsStream("/id/mky/roshambo/fonts/DarumadropOne-Regular.ttf"), 10);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/id/mky/roshambo/altView/startView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Ro-Sham-Bo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/id/mky/roshambo/character/papier.png")).toExternalForm()));

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}


/* docks
*   https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/BufferedReader.html
*   https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/FXMLLoader.html
*   https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
*   https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html
*   https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/PropertyValueFactory.html
*   https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ObservableList.html
*   https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Parent.html
*
*
* */

