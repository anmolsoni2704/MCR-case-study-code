package com.TicTacToe.strategies.winningStrategies;

import com.TicTacToe.models.Board;
import com.TicTacToe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Move move, Board board);
}
