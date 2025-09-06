package com.glints.takehometest.controller;

import com.glints.takehometest.bean.Game;
import com.glints.takehometest.bean.Result;
import com.glints.takehometest.bean.WinnerEvaluator;
import com.glints.takehometest.bean.player.HumanPlayer;
import com.glints.takehometest.bean.player.RandomPlayer;
import com.glints.takehometest.bean.player.UnbeatablePlayer;
import com.glints.takehometest.service.ITicTacToeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ITicTacToeService ticTacToeService;

    public TicTacToeController(ITicTacToeService ticTacToeService) {
        this.ticTacToeService = ticTacToeService;
    }

    @PostMapping("/api/play/random")
    public ResponseEntity<Result> playRandom(@RequestBody Game game) {
        try {
            Result result = this.ticTacToeService.play(game, new RandomPlayer(game.getPlayerLetter()));
            if (result == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/play/unbeatable")
    public ResponseEntity<Result> playUnbeatable(@RequestBody Game game) {
        try {
            Result result = this.ticTacToeService.play(game, new UnbeatablePlayer(game.getPlayerLetter(), new WinnerEvaluator()));
            if (result == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/play/human")
    public ResponseEntity<Result> playHuman(@RequestBody Game game) {
        try {
            Result result = this.ticTacToeService.play(game, new HumanPlayer(game.getPlayerLetter()));
            if (result == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
