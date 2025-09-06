package com.glints.takehometest.bean;

import java.util.ArrayList;
import java.util.List;

public class BoardAvailabiltyChecker {
    String[][] board;

    public BoardAvailabiltyChecker(String[][] board) {
        this.board = board;
    }

    public List<int[]> getAvailabilityPositions() {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ("".equals(board[i][j])) {
                    result.add(new int[]{i,j});
                }
            }
        }
        return result;
    }
}
