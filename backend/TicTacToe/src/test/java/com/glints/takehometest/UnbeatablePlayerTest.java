package com.glints.takehometest;

import com.glints.takehometest.bean.WinnerEvaluator;
import com.glints.takehometest.bean.player.Player;
import com.glints.takehometest.bean.player.UnbeatablePlayer;
import org.junit.jupiter.api.Test;

public class UnbeatablePlayerTest {

    @Test
    public void UnbeatablePlayerAsX_BlocksOpponentToWin() {
        String[][] currentBoard = new String[][]{
                {"x", "", ""},
                {"x", "o", "o"},
                {"", "", "o"},

        };

        String[][] expected = new String[][]{
                {"x", "", ""},
                {"x", "o", "o"},
                {"x", "", "o"},

        };
        assertMove(currentBoard, expected, new UnbeatablePlayer("x", new WinnerEvaluator()), 3);
    }

    @Test
    public void UnbeatablePlayerAsO_BlocksOpponentToWin() {
        String[][] currentBoard = new String[][]{
                {"x", "o", "o"},
                {"", "x", ""},
                {"", "", ""},

        };

        String[][] expected = new String[][]{
                {"x", "o", "o"},
                {"", "x", ""},
                {"", "", "o"},

        };
        assertMove(currentBoard, expected, new UnbeatablePlayer("o", new WinnerEvaluator()), 3);
    }

    private void assertMove(String[][] currentBoardState, String[][] expected, Player player, int totalConsecutive) {
        PlayerTest playerTest = new PlayerTest(player);
        playerTest.assertPlayerMoveIsEqualAsExpected(currentBoardState, expected, totalConsecutive);
    }
}
