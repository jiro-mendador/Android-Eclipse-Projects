package com.mendador.pairinggame;

public class Player {
    static String playerName;

    Player(String playerName) {
        this.playerName = playerName;
    }

    public static String getPlayerName() {
        return playerName;
    }
}
