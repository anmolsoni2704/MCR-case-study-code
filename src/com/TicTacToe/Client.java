package com.TicTacToe;

import com.TicTacToe.controllers.GameController;
import com.TicTacToe.exceptions.InvalidBotCountException;
import com.TicTacToe.exceptions.InvalidMoveException;
import com.TicTacToe.exceptions.InvalidPlayerCountException;
import com.TicTacToe.models.*;
import com.TicTacToe.strategies.winningStrategies.ColumnWinningStrategy;
import com.TicTacToe.strategies.winningStrategies.DiagonalWinningStrategy;
import com.TicTacToe.strategies.winningStrategies.RowWinningStrategy;
import com.TicTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidMoveException {
//        Player p1 = new Player("Umang", new Symbol('X'), PlayerType.HUMAN);
//
//        //Game.getBuilder().setPlayers().setsdfvjk().set()..build()
//
//        //Game game = new Game(2, new ArrayList<>(), new ArrayList<>());
//
//        Game game = Game.getBuilder().
//                setDimension(3).
//                setPlayers(new ArrayList<>()).
//                setWinningStrategies(new ArrayList<>()).
//                build();

        int dimension = 3;
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Anmol", new Symbol('X'), PlayerType.HUMAN));
        playerList.add(new Bot("intelligentBot", new Symbol('O'), BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());



        GameController gameController = new GameController();
        Game game = gameController.startGame(dimension,
                playerList,
                winningStrategies);

        //gameController.printBoard(game);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            /*
            1. Print the board
            2. Ask the user to choose where to make the move
             */
            gameController.printBoard(game);

            gameController.makeMove(game);
        }

        if(game.getGameState().equals(GameState.ENDED)){
            gameController.printBoard(game);
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        }else{
            System.out.println("Game is drawn");
        }

        //Game game = GameController.startGame();
    }
}
