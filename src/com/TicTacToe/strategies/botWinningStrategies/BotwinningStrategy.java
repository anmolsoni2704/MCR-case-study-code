package com.TicTacToe.strategies.botWinningStrategies;

import com.TicTacToe.models.Board;
import com.TicTacToe.models.Move;

public interface BotwinningStrategy {
    Move makeMove(Board board);
}
