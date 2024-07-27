package com.TicTacToe.strategies.winningStrategies;

import com.TicTacToe.models.*;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, Map< Symbol, Integer>> rowHashMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        Cell cell = move.getCell();
        int row = cell.getRow();

        Player player = move.getPlayer();
        Symbol symbol = player.getSymbol();

        if(!rowHashMaps.containsKey(row)){
            rowHashMaps.put(row, new HashMap<Symbol, Integer>());
        }

        Map<Symbol, Integer> hm = rowHashMaps.get(row);

        if(!hm.containsKey(symbol)){
            hm.put(symbol, 0);
        }

        hm.put(symbol, hm.get(symbol) + 1);

        int count = hm.get(symbol);

        return count == board.getDimension();

    }
}
