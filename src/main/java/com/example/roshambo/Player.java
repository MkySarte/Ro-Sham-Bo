package com.example.roshambo;

public class Player {
    private String name;
    private int wins;

    public Player(String name, int wins) {
        this.name = name;
        this.wins = wins;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }
}
