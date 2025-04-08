package com.example.roshambo;
/*
*
* Hintergrund
*
* */
import java.util.HashMap;
import java.util.Map;

public class Background {

    public Map<String, String> getImages() {
        Map<String, String> background = new HashMap<>();
        background.put("/com/example/roshambo/character/papierback.png", "/com/example/roshambo/landscape/papier.jpg");
        background.put("/com/example/roshambo/character/schereback.png", "/com/example/roshambo/landscape/schere.jpg");
        background.put("/com/example/roshambo/character/steinback.png", "/com/example/roshambo/landscape/stein.jpg");

        return background;
    }


}
