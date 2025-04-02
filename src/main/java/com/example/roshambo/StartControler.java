package com.example.roshambo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.jar.Attributes;

public class StartControler {

    //erste Seite Beschreibung
    @FXML private Pane ersteSeite;
    @FXML private Button weiter1;

    //zweite Seite Bilder
    @FXML private Pane zweiteSeite;
    @FXML private Button weiter2;

    //Letzte Seite
    @FXML private Button rdy;
    @FXML private Button rangliste;
    @FXML private TextField namenField;

    public void nxt2() {
        zweiteSeite.setVisible(false);
    }
    public void nxt1() {
        ersteSeite.setVisible(false);
    }

    @FXML
    public void initialize() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/DarumadropOne-Regular.ttf"), 10);
    }

    @FXML
    public void rdyToPlay(ActionEvent event) throws Exception {
        String name = namenField.getText().trim();
        if (name.isEmpty()) {
            System.out.println("⚠️ Gib einen Namen ein!");
            //ein Label noch setzen
            return;
        }

        // FXML + Controller laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/roshambo/roShamBo_View.fxml"));
        Parent root = loader.load();

        // Controller holen und Namen setzen
        RoShamBo controller = loader.getController();
        controller.setUserName(name);

        // Fenster wechseln
        Stage stage = (Stage) namenField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void zeigeRangliste() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/roshambo/rankView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) rangliste.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}

