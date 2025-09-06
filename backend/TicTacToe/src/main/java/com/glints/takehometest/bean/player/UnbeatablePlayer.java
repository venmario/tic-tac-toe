package com.glints.takehometest.bean.player;

import com.glints.takehometest.bean.WinnerEvaluator;

public class UnbeatablePlayer extends Player {
    WinnerEvaluator winnerEvaluator;

    public UnbeatablePlayer(String letter, WinnerEvaluator winnerEvaluator) {
        super(letter);
        this.winnerEvaluator = winnerEvaluator;
    }

    @Override
    public String[][] move(String[][] currentBoardState, int totalConsecutive) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
        for (int i = 0; i < currentBoardState.length; i++) {
            for (int j = 0; j < currentBoardState[i].length; j++) {
                if ("".equals(currentBoardState[i][j])) {
                    currentBoardState[i][j] = this.letter;
                    int score = minimax(currentBoardState, 0, false, totalConsecutive);
                    currentBoardState[i][j] = "";
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        currentBoardState[bestMove[0]][bestMove[1]] = this.letter;
        return currentBoardState;
    }

    private int minimax(String[][] board, int depth, boolean isMax, int totalConsecutive) {
        String winner = winnerEvaluator.calculate(board, totalConsecutive);
        if (!"".equals(winner)) {
            if (winner.equals("Tie")) return 0;
            return winner.equals(letter) ? 10 - depth : depth - 10;
        }

        if (isMax) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if ("".equals(board[i][j])) {
                        board[i][j] = this.letter;
                        int score = minimax(board, depth + 1, false, totalConsecutive);
                        board[i][j] = "";
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if ("".equals(board[i][j])) {
                        board[i][j] = this.opponentLetter;
                        int score = minimax(board, depth + 1, true, totalConsecutive);
                        board[i][j] = "";
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
