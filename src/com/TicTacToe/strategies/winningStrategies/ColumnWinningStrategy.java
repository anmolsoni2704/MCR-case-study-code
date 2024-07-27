package com.TicTacToe.strategies.winningStrategies;

import com.TicTacToe.models.*;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> colHashMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        Cell cell = move.getCell();
        int col = cell.getCol();

        Player player = move.getPlayer();
        Symbol symbol = player.getSymbol();

        if(!colHashMaps.containsKey(col)){
            colHashMaps.put(col, new HashMap<Symbol, Integer>());
        }

        Map<Symbol, Integer> hm = colHashMaps.get(col);

        if(!hm.containsKey(symbol)){
            hm.put(symbol, 0);
        }

        hm.put(symbol, hm.get(symbol) + 1);

        int count = hm.get(symbol);

        return count == board.getDimension();

    }
}
