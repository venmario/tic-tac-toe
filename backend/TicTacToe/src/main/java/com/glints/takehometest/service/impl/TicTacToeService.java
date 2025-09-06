package com.glints.takehometest.service.impl;

import com.glints.takehometest.bean.*;
import com.glints.takehometest.bean.player.Player;
import com.glints.takehometest.service.ITicTacToeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeService implements ITicTacToeService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final BoardToArrayConverter boardToArrayConverter;
    private final WinnerEvaluator winnerEvaluator;
    private final BoardStringify boardStringify;

    public TicTacToeService(BoardToArrayConverter boardToArrayConverter, WinnerEvaluator winnerEvaluator, BoardStringify boardStringify) {
        this.boardToArrayConverter = boardToArrayConverter;
        this.winnerEvaluator = winnerEvaluator;
        this.boardStringify = boardStringify;
    }

    @Override
    public Result play(Game game, Player player) {
        try {
            String[][] board = boardToArrayConverter.convertBoardToArray(game.getBoard(), game.getBoardGrid());

            String[][] newBoardSate = player.move(board, game.getTotalConsecutive());

            String winner = winnerEvaluator.calculate(newBoardSate, game.getTotalConsecutive());
            String boardString = boardStringify.toBoardString(newBoardSate);
            return new Result(boardString, winner);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
