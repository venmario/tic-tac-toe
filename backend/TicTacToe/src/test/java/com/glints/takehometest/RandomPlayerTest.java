package com.glints.takehometest;

import com.glints.takehometest.bean.player.Player;
import com.glints.takehometest.bean.player.RandomPlayer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RandomPlayerTest {

    @Test
    public void RandomPlayer_Moves_RandomPoint() {
        String[][] currentBoard = new String[][]{
                {"x", "o", "o"},
                {"", "x", ""},
                {"", "", ""},

        };
        Player player = new RandomPlayer("x");
        System.out.println(Arrays.deepToString(player.move(currentBoard, 3)));
        assertArrayNotEquals(currentBoard, new RandomPlayer("x"), 3);
    }

    @Test
    public void RandomPlayer_NoMovesAvailable() {
        String[][] currentBoard = new String[][]{
                {"x", "o", "o"},
                {"o", "o", "x"},
                {"x", "x", "o"},

        };
        Player player = new RandomPlayer("x");
        assertArrayEqual(currentBoard, currentBoard, player, 3);
    }


    private void assertArrayNotEquals(String[][] currentBoardState, Player player, int totalConsecutive) {
        PlayerTest playerTest = new PlayerTest(player);
        playerTest.assertPlayerMoveIsNotEqualAsExpected(currentBoardState, totalConsecutive);
    }

    private void assertArrayEqual(String[][] currentBoardState, String[][] expectd, Player player, int totalConsecutive) {
        PlayerTest playerTest = new PlayerTest(player);
        playerTest.assertPlayerMoveIsEqualAsExpected(currentBoardState, expectd, totalConsecutive);
    }
}
