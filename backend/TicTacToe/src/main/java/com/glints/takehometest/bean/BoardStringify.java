package com.glints.takehometest.bean;

import org.springframework.stereotype.Component;

@Component
public class BoardStringify {

    public String toBoardString(String[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j] == null ? "" : board[i][j]);
                if (j < board[i].length - 1) {
                    sb.append(",");
                }
            }
            if (i < board.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
