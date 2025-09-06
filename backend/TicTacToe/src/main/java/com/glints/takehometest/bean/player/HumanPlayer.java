package com.glints.takehometest.bean.player;

public class HumanPlayer extends Player {
    public HumanPlayer(String letter) {
        super(letter);
    }

    @Override
    public String[][] move(String[][] currentBoardState, int totalConsecutive) {
        return currentBoardState;
    }
}
