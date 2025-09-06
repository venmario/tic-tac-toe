package com.glints.takehometest;

import com.glints.takehometest.bean.WinnerEvaluator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculateWinnerTest {

    @Test
    public void CalculateWinner_When3x3_Tie_ReturnTie() {
        String[][] board = new String[][]{
                {"x", "o", "x"},
                {"o", "x", "x"},
                {"o", "x", "o"}
        };
        assertWinner("Tie", board, 3);
    }

    @Test
    public void CalculateWinner_When3x3_XWin_ReturnX() {
        String[][] board = new String[][]{
                {"x", "o", ""},
                {"x", "o", ""},
                {"x", "", ""}
        };
        assertWinner("x", board, 3);

    }

    @Test
    public void CalculateWinner_When3x3_OWin_ReturnO() {
        String[][] board = new String[][]{
                {"o", "", ""},
                {"x", "o", ""},
                {"x", "", "o"}
        };
        assertWinner("o", board, 3);
    }

    @Test
    public void CalculateWinner_When3x3_UndoneGame_ReturnEmpty() {
        String[][] board = new String[][]{
                {"o", "", ""},
                {"x", "o", "o"},
                {"x", "", ""}
        };
        assertWinner("", board, 3);
    }

    @Test
    public void CalculateWinner_When5x5_OWin_ReturnO() {
        String[][] board = new String[][]{
                {"o", "", "o", "", ""},
                {"", "x", "o", "", ""},
                {"", "x", "o", "", ""},
                {"", "", "", "o", ""},
                {"", "x", "", "", "x"}
        };
        assertWinner("o", board, 3);
    }

    @Test
    public void CalculateWinner_When5x5_OWin_4TotalConsecutive_ReturnO() {
        String[][] board = new String[][]{
                {"x", "o", "x", "", ""},
                {"", "o", "x", "", ""},
                {"", "o", "x", "", ""},
                {"", "o", "", "x", ""},
                {"", "o", "", "", "x"}
        };
        assertWinner("o", board, 4);
    }

    @Test
    public void CalculateWinner_When5x4_OWin_3TotalConsecutive_ReturnO() {
        String[][] board = new String[][]{
                {"x", "", "", "", ""},
                {"", "o", "x", "", ""},
                {"", "o", "x", "", ""},
                {"", "o", "", "", ""},
        };
        assertWinner("o", board, 3);
    }

    @Test
    public void CalculateWinner_When5x4_XWin_3TotalConsecutive_ReturnX() {
        String[][] board = new String[][]{
                {"x", "", "", "", ""},
                {"", "o", "o", "", "x"},
                {"", "o", "", "x", ""},
                {"", "", "x", "", ""},
        };
        assertWinner("x", board, 3);
    }

    @Test
    public void CalculateWinner_When4x5_OWin_3TotalConsecutive_ReturnO() {
        String[][] board = new String[][]{
                {"x", "", "", ""},
                {"o", "o", "o", ""},
                {"", "o", "o", "x"},
                {"x", "", "x", ""},
                {"", "", "", "x"},
        };
        assertWinner("o", board, 3);
    }

    @Test
    public void CalculateWinner_When4x5_UndoneGame_3TotalConsecutive_ReturnEmpty() {
        String[][] board = new String[][]{
                {"x", "", "", ""},
                {"o", "", "o", ""},
                {"", "o", "o", "x"},
                {"x", "", "x", ""},
                {"", "", "", "x"},
        };
        assertWinner("", board, 3);
    }

    private void assertWinner(String expected, String[][] board, int totalConsecutive) {
        WinnerEvaluator winnerEvaluator = new WinnerEvaluator();
        Assertions.assertEquals(expected, winnerEvaluator.calculate(board, totalConsecutive));
    }
}
