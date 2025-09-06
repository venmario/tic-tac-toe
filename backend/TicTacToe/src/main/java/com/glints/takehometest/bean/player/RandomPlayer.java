package com.glints.takehometest.bean.player;

import com.glints.takehometest.bean.BoardAvailabiltyChecker;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {
    public RandomPlayer(String letter) {
        super(letter);
    }

    @Override
    public String[][] move(String[][] currentBoardState, int totalConsecutive) {
        int[] randomMove = getRandomMove(currentBoardState);
        if (randomMove == null) return currentBoardState;
        currentBoardState[randomMove[0]][randomMove[1]] = letter;
        return currentBoardState;
    }

    private int[] getRandomMove(String[][] board) {
        BoardAvailabiltyChecker boardAvailabiltyChecker = new BoardAvailabiltyChecker(board);
        List<int[]> availablePoints = boardAvailabiltyChecker.getAvailabilityPositions();
        if (availablePoints.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(availablePoints.size());
        return availablePoints.get(randomIndex);
    }
}
