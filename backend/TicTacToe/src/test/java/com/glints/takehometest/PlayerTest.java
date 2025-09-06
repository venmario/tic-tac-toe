package com.glints.takehometest;

import com.glints.takehometest.bean.player.Player;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class PlayerTest {

    private final Player player;

    public PlayerTest(Player player) {
        this.player = player;
    }

    public void assertPlayerMoveIsEqualAsExpected(String[][] currentBoard, String[][] expectedAnswer, int totalConsecutive) {
        Assertions.assertArrayEquals(expectedAnswer, player.move(currentBoard, totalConsecutive));
    }

    public void assertPlayerMoveIsNotEqualAsExpected(String[][] currentBoard, int totalConsecutive) {
        Assertions.assertTrue(Arrays.deepEquals(currentBoard, player.move(currentBoard, totalConsecutive)));
    }
}
