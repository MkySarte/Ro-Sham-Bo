package com.example.roshambo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RoShamBo {

    @FXML
    private ImageView background;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView botImage;
    @FXML
    private Button next;
    @FXML
    private Button start;
    @FXML
    private Label simpleText;

    @FXML
    private ImageView life1;
    @FXML
    private ImageView life2;
    @FXML
    private ImageView life3;
    @FXML
    private ImageView life4;
    @FXML
    private ImageView life5;
    @FXML
    private ImageView life6;

    private String userName;
    private String winner;
    private int counterUser = 3;
    private int counterBot = 3;
    private int currentIndex = 0; // Spieler-Index
    private int currentIndex2 = -1; // Bot-Index
    private String lastBGPath;
    private Map<String, String> p1Liste;
    private Map<String, String> p2Liste;
    private Map<String, String> bgListe;
    private List<String> p1Keys;
    private List<String> p2Keys;
    private Timeline p1ShakeTimeline;
    //private Timeline botPoke;
    private Timeline shake;
    private Random random = new Random();

    public void setUserName(String userName){
        this.userName = userName;
        if (simpleText != null) {
            simpleText.setText("Hallo " + userName + "! Wähle dein Monster aus!");
        }
    }


    @FXML
    public void initialize() {
        Player1 p1 = new Player1();
        p1Liste = p1.getImages();

        Player2 p2 = new Player2();
        p2Liste = p2.getImages();

        Background bg = new Background();
        bgListe = bg.getImages(); // <- das bleibt so!

        p1Keys = new ArrayList<>(p1Liste.keySet());
        p2Keys = new ArrayList<>(p2Liste.keySet());

        String pfad = p1Keys.get(currentIndex);
        Image erste = new Image(getClass().getResource(pfad).toExternalForm());
        playerImage.setImage(erste);
    }


    @FXML
    public void nextPoki() {
        System.out.println("Nächstes Bild");
        currentIndex = (currentIndex + 1) % p1Liste.size();

        String pfad = p1Keys.get(currentIndex);
        Image image = new Image(getClass().getResource(pfad).toExternalForm());

        String bgPfad = bgListe.get(pfad);
        if (bgPfad != null) {
            lastBGPath = bgPfad;
            URL url = getClass().getResource(bgPfad);
            if (url != null) {
                background.setImage(new Image(url.toExternalForm()));
            } else {
                System.out.println("⚠️ Bild nicht gefunden: " + bgPfad);
            }
        } else {
            System.out.println("⚠️ Kein Eintrag in bgListe für: " + pfad);
        }


        playerImage.setImage(image);
    }

    @FXML
    public void los() {

        next.setVisible(false);

        Image fightBackground = new Image(getClass().getResource("/com/example/roshambo/landscape/fight.jpg").toExternalForm());
        background.setImage(fightBackground);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(10);

        KeyFrame frame = new KeyFrame(Duration.millis(370), e -> {
            currentIndex2 = random.nextInt(p2Keys.size());
            String pfad = p2Keys.get(currentIndex2);
            Image img = new Image(getClass().getResource(pfad).toExternalForm());
            botImage.setImage(img);
        });

        startP1Shake();
        timeline.getKeyFrames().add(frame);
        timeline.setOnFinished(e -> {
            p1ShakeTimeline.stop(); // Stoppt das Zittern
            playerImage.setTranslateX(0);    // Setzt p1 wieder gerade
            auswerten();            // Danach kommt Ergebnis
        });
        simpleText.setText("...");
        timeline.play();

    }

    private void auswerten() {

        String p1Entscheidung = p1Liste.get(p1Keys.get(currentIndex));
        String p2Entscheidung = p2Liste.get(p2Keys.get(currentIndex2));

        next.setVisible(false);

        System.out.println("P1 wählt: " + p1Entscheidung);
        System.out.println("P2 wählt: " + p2Entscheidung);

        if (p1Entscheidung.equals(p2Entscheidung)) {
            simpleText.setText("Unentschieden!");
            System.out.println("Unentschieden!");
        }
        else if (
                (p1Entscheidung.equals("Schere") && p2Entscheidung.equals("Papier")) ||
                        (p1Entscheidung.equals("Papier") && p2Entscheidung.equals("Stein")) ||
                        (p1Entscheidung.equals("Stein") && p2Entscheidung.equals("Schere"))
        ) {
            System.out.println("P1 gewinnt!");
            simpleText.setText(userName + " gewinnt!");
            counterBot--;

            if (counterBot == 2) {
                //shakeImage(life1);
                life1.setVisible(false);
                //shakeImage(life1);

            } else if (counterBot == 1) {
                //shakeImage(life2);
                life2.setVisible(false);
                //shakeImage(life2);

            } else if (counterBot == 0) {
                //shakeImage(life3);
                life3.setVisible(false);
                //shakeImage(life3);

                start.setVisible(false);
                next.setVisible(false);
                winner = userName;

                Timeline delay = new Timeline(new KeyFrame(Duration.seconds(2), evt -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/roshambo/winnerView.fxml"));
                        Parent root = loader.load();

                        WinnerController controller = loader.getController();
                        controller.setUserName(userName);
                        controller.setWinner(winner);

                        Stage stage = (Stage) simpleText.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }));
                delay.play();
                return;
            }
        }
        else {
            System.out.println("P2 gewinnt!");
            simpleText.setText("Bot gewinnt!");
            counterUser--;

            if (counterUser == 2) {
                life4.setVisible(false);
               // nicht mehr nötig
                // shakeImage(life4);

            } else if (counterUser == 1) {
                life5.setVisible(false);
                // nicht mehr nötig
                //shakeImage(life5);

            } else if (counterUser == 0) {
                life6.setVisible(false);
                // nicht mehr nötig
                //shakeImage(life6);

                start.setVisible(false);
                next.setVisible(false);
                winner = "Bot";


                //Timeline = verzögern oder wiederholen(Führt den folgenden Code NACH 2 Sekunden aus)
                Timeline delay = new Timeline(new KeyFrame(Duration.seconds(2), evt -> {
                    try {
                        // Lädt die Gewinner-Ansicht aus winnerView.fxml
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/roshambo/winnerView.fxml"));
                        Parent root = loader.load();

                        // Holt den Controller der Gewinner-Ansicht
                        WinnerController controller = loader.getController();

                        // Übergibt Spielername und Gewinner
                        controller.setUserName(userName);
                        controller.setWinner(winner);

                        // Holt das aktuelle Fenster (Stage)
                        Stage stage = (Stage) simpleText.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException ex) {
                        // Falls etwas beim Laden schiefläuft → Fehler ausgeben
                        ex.printStackTrace();
                    }
                }));
                //startet verzögert OBEN um wie viel
                delay.play();
                return;
            }
        }

        if (lastBGPath != null) {
            URL url = getClass().getResource(lastBGPath);
            if (url != null) {
                background.setImage(new Image(url.toExternalForm()));
            }
        }

        if (counterUser > 0 && counterBot > 0) {
            next.setVisible(true);
        }
    }



    private void startP1Shake() {
        p1ShakeTimeline = new Timeline(
                new KeyFrame(Duration.millis(100), e -> playerImage.setTranslateX(-10)),
                new KeyFrame(Duration.millis(200), e -> playerImage.setTranslateX(10)),
                new KeyFrame(Duration.millis(300), e -> playerImage.setTranslateX(0))
        );
        p1ShakeTimeline.setCycleCount(Timeline.INDEFINITE);
        p1ShakeTimeline.play();
    }

    /*private void pokeShake() {
        if (botPoke != null) {
            botPoke.stop(); // vorheriges Zittern beenden
            botImage.setTranslateX(0); // Position zurücksetzen
        }

        botPoke = new Timeline(
                new KeyFrame(Duration.millis(50), e -> botImage.setTranslateX(-10)),
                new KeyFrame(Duration.millis(100), e -> botImage.setTranslateX(10)),
                new KeyFrame(Duration.millis(150), e -> botImage.setTranslateX(0))
        );
        botPoke.setCycleCount(3); // nur ein paar Mal wackeln
        botPoke.play();
    }*/

   /* private void shakeImage(ImageView imageView) {
        if (shake != null) {
            shake.stop(); // vorheriges Zittern beenden
            imageView.setTranslateX(0); // Position zurücksetzen
        }

        Timeline shake = new Timeline(
                new KeyFrame(Duration.millis(50), e -> imageView.setTranslateX(-5)),
                new KeyFrame(Duration.millis(100), e -> imageView.setTranslateX(5)),
                new KeyFrame(Duration.millis(150), e -> imageView.setTranslateX(0))
        );
        shake.setCycleCount(3);
        shake.play();
    }

    */
}
