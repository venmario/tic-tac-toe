package com.glints.takehometest.bean;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class WinnerEvaluator {

    public String calculate(String[][] board, int totalConsecutive) {
        String result = "";
        boolean draw = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String player = board[i][j];
                if (!StringUtils.hasLength(player)) {
                    draw = false;
                    continue;
                }
                if (checkDirection(board, i, j, 0, 1, player, totalConsecutive) || //right
                    checkDirection(board, i, j, 1, 0, player, totalConsecutive) || //down
                    checkDirection(board, i, j, 1, 1, player, totalConsecutive) || //topleft -> bottomright
                    checkDirection(board, i, j, 1, -1, player, totalConsecutive)) {//topright -> bottomleft
                    return player;
                }
            }
        }
        if (draw) return "Tie";
        return result;
    }

    private boolean checkDirection(String[][] board, int row, int col, int rOffset, int cOffset, String player, int totalConsecutive) {
        //[ [x,x,x],
        //  [o,o,o],
        //  [x,x,x] ]
        int width = board.length;
        int length = board[0].length;
        for (int i = 1; i < totalConsecutive; i++) {
            int nextRow = row + i * rOffset;
            int nextCol = col + i * cOffset;
            if (nextRow < 0 || nextRow >= width || nextCol < 0 || nextCol >= length) {
                return false;
            }
            if (!board[nextRow][nextCol].equals(player)) {
                return false;
            }
        }
        return true;
    }
}
