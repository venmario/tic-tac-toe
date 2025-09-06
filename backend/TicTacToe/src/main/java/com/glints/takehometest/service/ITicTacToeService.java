package com.glints.takehometest.service;

import com.glints.takehometest.bean.Game;
import com.glints.takehometest.bean.Result;
import com.glints.takehometest.bean.player.Player;

public interface ITicTacToeService {
    Result play(Game game, Player player);
}
