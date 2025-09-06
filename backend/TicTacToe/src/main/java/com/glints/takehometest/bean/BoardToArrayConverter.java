package com.glints.takehometest.bean;

import org.springframework.stereotype.Component;

@Component
public class BoardToArrayConverter {
    public String[][] convertBoardToArray(String board, BoardGrid boardGrid) {
        //[ [x,x,x],
        //  [o,o,o],
        //  [x,x,x] ]
        String[][] result = new String[boardGrid.getY()][boardGrid.getX()];

        String[] row = board.split(";");// ["x,x,x", "o,o,o", "x,x,x"]
        int length = row.length;
        for (int i = 0; i < length; i++) {
            String[] col = row[i].split(",", -1);
            for (int j = 0; j < col.length; j++) {
                result[i][j] = col[j];
            }
        }

        return result;
    }
}
