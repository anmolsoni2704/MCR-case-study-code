package com.TicTacToe.controllers;

import com.TicTacToe.exceptions.InvalidBotCountException;
import com.TicTacToe.exceptions.InvalidMoveException;
import com.TicTacToe.exceptions.InvalidPlayerCountException;
import com.TicTacToe.models.Game;
import com.TicTacToe.models.GameState;
import com.TicTacToe.models.Player;
import com.TicTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> playerList, List<WinningStrategy> winningStrategies) throws InvalidBotCountException, InvalidPlayerCountException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(playerList)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
