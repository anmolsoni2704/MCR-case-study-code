package com.TicTacToe.strategies.winningStrategies;

import com.TicTacToe.models.Board;
import com.TicTacToe.models.Cell;
import com.TicTacToe.models.Move;
import com.TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol,Integer> leftDiagonalMap = new HashMap<>();
    private Map<Symbol,Integer> rightDiagonalMap = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            if(!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol, 0);
            }
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol) + 1);

            int count = leftDiagonalMap.get(symbol);
            return count == board.getDimension();
        }

        if(row + col == board.getDimension() -1) {
            if(!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol, 0);
            }
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol) + 1);

            int count = rightDiagonalMap.get(symbol);
            return count == board.getDimension();
        }

        return false;
    }
}
