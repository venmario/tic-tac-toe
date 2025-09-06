package com.glints.takehometest;

import com.glints.takehometest.bean.BoardGrid;
import com.glints.takehometest.bean.BoardToArrayConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardToArrayConverterTest {

    @Test
    void EmptyString3x3_Return2dArray3x3() {
        String board = ",,;,,;,,";
        String[][] expected = new String[][]{{"", "", ""}, {"", "", ""}, {"", "", ""}};
        assertStringto2dArray(expected, board, new BoardGrid(3, 3));

    }

    @Test
    void String3x3_Return2dArray3x3() {
        String board = "x,x,x;o,o,o;x,x,x";
        String[][] expected = new String[][]{{"x", "x", "x"}, {"o", "o", "o"}, {"x", "x", "x"}};
        assertStringto2dArray(expected, board, new BoardGrid(3, 3));

    }

    @Test
    void String5x5_Return2dArray5x5() {
        String board = ",,,,;,,,,;,,,,;,,,,;,,,,";
        String[][] expected = new String[][]{
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""}};
        assertStringto2dArray(expected, board, new BoardGrid(5, 5));
    }

    @Test
    void String5x4_Return2dArray5x4() {
        String board = ",,,,;,,,,;,,,,;,,,,";
        String[][] expected = new String[][]{
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""}};
        assertStringto2dArray(expected, board, new BoardGrid(5, 4));
    }

    @Test
    void String4x5_Return2dArray4x5() {
        String board = "o,,,;,,,;,,,;,,,;,,,x";
        String[][] expected = new String[][]{
                {"o", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", "x"}};
        assertStringto2dArray(expected, board, new BoardGrid(4, 5));
    }

    private void assertStringto2dArray(String[][] expected, String board, BoardGrid boardGrid) {
        BoardToArrayConverter boardToArrayConverter = new BoardToArrayConverter();
        Assertions.assertArrayEquals(expected, boardToArrayConverter.convertBoardToArray(board, boardGrid));
    }
}
