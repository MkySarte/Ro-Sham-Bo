package com.example.roshambo;

import java.util.HashMap;
import java.util.Map;

public class Player2 {
    public Map<String, String> getImages() {
        Map<String, String> botImages = new HashMap<>();
        botImages.put("/com/example/roshambo/character/stein.png", "Stein");
        botImages.put("/com/example/roshambo/character/schere.png", "Schere");
        botImages.put("/com/example/roshambo/character/papier.png", "Papier");
        return botImages;
    }
}

