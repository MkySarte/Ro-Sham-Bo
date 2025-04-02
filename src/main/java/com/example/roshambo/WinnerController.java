package com.example.roshambo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class WinnerController {
    @FXML private Pane ersteSeite;
    @FXML private Button rangliste;
    @FXML private Button again;
    @FXML private Label winnerText;
    @FXML private String winner;
    private String userName;


    @FXML
    public void initialize() {

    }
    @FXML
    public void again() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/roshambo/roShamBo_View.fxml"));
        Parent root = loader.load();

        RoShamBo controller = loader.getController();
        controller.setUserName(this.userName);
                
        Stage stage = (Stage) again.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setUserName(String userName) {
       this.userName = userName;
    }

    /**
     *
     * verbessert

    public void setWinner(String winner) {
       this.winner = winner;
       if(winnerText != null) {
           winnerText.setText("Der Champion ist " + winner);
       }

    }
    */
    public void setWinner(String winner) {
        this.winner = winner;
        if (winnerText != null) {
            winnerText.setText("Der Champion ist " + winner);
        }

        // In Datei speichern
        try (FileWriter writer = new FileWriter("ranklist.txt", true)) {
            writer.write(winner + "\n"); // jeder Gewinner in einer neuen Zeile
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void zeigeRangliste() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/roshambo/rankView.fxml"));

        //Diese Klasse verarbeitet alle hierarchischen Szenendiagrammoperationen, einschließlich Hinzufügen/Entfernen von untergeordneten Knoten,
        // Markieren von Zweigen als fehlerhaft für Layout und Rendering,
        // Auswählen, Berechnung von Grenzen und Ausführen des Layoutdurchlaufs bei jedem Impuls.
        Parent root = loader.load();

        Stage stage = (Stage) rangliste.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
