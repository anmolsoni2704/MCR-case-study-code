package com.TicTacToe.models;

import com.TicTacToe.exceptions.InvalidBotCountException;
import com.TicTacToe.exceptions.InvalidMoveException;
import com.TicTacToe.exceptions.InvalidPlayerCountException;
import com.TicTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    private boolean validateMove(Move move) {
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();

        //validated if the row and col numbers are within boundary
        if (row < 0 || col < 0 || row >= board.getDimension() || col >= board.getDimension()) {
            return false;
        }

        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)) {
            return false;
        }
        return true;
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);

        System.out.println("It is " + currentPlayer.getName() + "'s turn");

        Move move = currentPlayer.makeMove(board);

        if (!validateMove(move)) {
            throw new InvalidMoveException("Move is not valid");
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

        if (checkWinner(finalMove)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(move, board)) {
                return true;
            }
        }
        return false;
    }

    public void printBoard(){
        board.print();
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType() == PlayerType.BOT) {
                    botCount++;
                }
            }
            if (botCount > 1) {
                throw new InvalidBotCountException("Count of BOTs are greater than 1");
            }
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if (players.size() != dimension - 1) {
                throw new InvalidPlayerCountException("Player count is invalid");
            }
        }

        private void validateUniqueSymbolsForEachPlayers() {

        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException {
            validateBotCount();
            validateUniqueSymbolsForEachPlayers();
            validatePlayerCount();
        }


        public Game build() throws InvalidPlayerCountException, InvalidBotCountException {
            //Before building the game, we should validate the game object
            validate();
            return new Game(
                    dimension,
                    players,
                    winningStrategies
            );
        }
    }
}
