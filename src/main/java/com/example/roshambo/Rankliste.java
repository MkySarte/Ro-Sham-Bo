package com.example.roshambo;
/*
* rangliste mit TXT
*
*
* */
import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map;

//https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
public class Rankliste {

    @FXML private Button playButton;
    @FXML private TableView<Player> rankTable;
    @FXML private TableColumn<Player, String> colPlayer;
    @FXML private TableColumn<Player, Integer> colWins;


    //https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ObservableList.html
    //Eine Liste, die es Zuhörern ermöglicht, Änderungen zu verfolgen, wenn sie auftreten
    private final ObservableList<Player> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        Font.loadFont(getClass().getResource("/com/example/roshambo/fonts/DarumadropOne-Regular.ttf").toExternalForm(), 12);

        colPlayer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colWins.setCellValueFactory(new PropertyValueFactory<>("wins"));

        // nur weil CSS für ultimate ist "Wins" so gesetzt
        Label winsHeader = new Label("Wins");
        winsHeader.setFont(Font.font("Darumadrop One", 24));
        winsHeader.setAlignment(Pos.CENTER);
        colWins.setText(null); // deaktiviert Standardtext
        colWins.setGraphic(winsHeader);

        // Spalte: Wins
        colWins.setCellFactory(column -> new TableCell<Player, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(item));
                    setFont(Font.font("Darumadrop One", 20));
                    setAlignment(Pos.CENTER);
                }
            }
        });

        // nur weil CSS für ultimate ist "Player" so gesetzt
        Label playerHeader = new Label("Player");
        playerHeader.setFont(Font.font("Darumadrop One", 24));
        playerHeader.setAlignment(Pos.CENTER);
        colPlayer.setText(null);
        colPlayer.setGraphic(playerHeader);

        colPlayer.setCellFactory(column -> new TableCell<Player, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setFont(Font.font("Darumadrop One", 20));
                    setAlignment(Pos.CENTER);
                }
            }
        });

        ladeRanklist();
    }

    private void ladeRanklist() {
        Map<String, Integer> winMap = new HashMap<>();
        //Liest Text aus einem Zeicheneingabestream und puffert Zeichen, um das effiziente Lesen von Zeichen, Arrays und Zeilen zu ermöglichen.
        try (BufferedReader reader = new BufferedReader(new FileReader("ranklist.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                winMap.put(line, winMap.getOrDefault(line, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        data.clear();
        for (Map.Entry<String, Integer> entry : winMap.entrySet()) {
            data.add(new Player(entry.getKey(), entry.getValue()));
        }

        rankTable.setItems(data);
    }
    @FXML
    private void onPlayClicked() {
        // zurück zum Spiel –
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/roshambo/startView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) playButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExitClicked() {
        System.exit(0);
    }
}


