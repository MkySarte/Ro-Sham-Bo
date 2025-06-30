Ich habe eine Startseite.fxml, die vom StartController gesteuert wird.

Diese Seite enthält:
Einen Begrüßungstext (z.B. „Willkommen“),
eine bildliche Darstellung, wie das Spiel funktioniert,
sowie ein Eingabefeld für den Benutzernamen.
Erst nachdem der Benutzer seinen Namen eingegeben hat, gelangt er zur nächsten Ansicht.
Man könnte diese Ansicht als „Landing Page“ bezeichnen, sie führt jedoch zur LobbyView, welche vom LobbyController gesteuert wird.
In der LobbyView hat der Benutzer folgende Möglichkeiten:
Gegen den Computer spielen (Solo),
oder den PvP-Modus auswählen.
Zusätzlich wird ihm ein Kreis angezeigt:
Ist dieser grün, sind mindestens zwei Spieler online, und ein PvP-Spiel wäre möglich.
Es wird nicht angezeigt, welche Spieler online sind – lediglich ein kleiner Kreis mit einer Zahl, die die Anzahl der Online-Spieler repräsentiert.
Außerdem gibt es einen Button „Rangliste“.
Diese wird serverseitig verwaltet – der Client sendet lediglich eine Anfrage, um sie abzurufen.
Wenn der Benutzer den PvP-Modus auswählt, wird die roshambo.fxml geladen.
Dort wählt er Schere, Stein oder Papier.
Hinweis:
Diese View (roshambo.fxml) muss für den PvP-Modus angepasst werden:
Das Avatar oben rechts (z.B. das Spielerbild) wird nur im Solo-Modus angezeigt, nicht im PvP-Modus.
Die Steuerung übernimmt der RoshamboController.
Er verarbeitet:
die Benutzer-Auswahl (Schere, Stein, Papier),
sendet diese Information als JSON an den Server.
Die Spiellogik (Wer gewinnt?) liegt nicht im Client, sondern auf dem Server.
Der Server antwortet mit dem Ergebnis (Gewonnen, Verloren, Unentschieden), woraufhin die passende Animation ausgeführt wird.