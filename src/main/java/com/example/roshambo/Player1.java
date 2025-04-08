package com.example.roshambo;
/*
* Player1
*
* */
import java.util.HashMap;
import java.util.Map;


    public class Player1 {
        public Map<String, String> getImages() {
            Map<String, String> playerImages = new HashMap<>();
            playerImages.put("/com/example/roshambo/character/steinback.png", "Stein");
            playerImages.put("/com/example/roshambo/character/schereback.png", "Schere");
            playerImages.put("/com/example/roshambo/character/papierback.png", "Papier");
            return playerImages;
        }
    }

