package com.glints.takehometest.bean.player;

public abstract class Player implements IPlayer {
    protected String letter;
    protected String opponentLetter;

    protected Player(String letter) {
        this.letter = letter;
        this.opponentLetter = "x".equals(letter) ? "o" : "x";
    }
}
