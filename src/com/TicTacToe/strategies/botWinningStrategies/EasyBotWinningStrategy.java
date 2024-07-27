package com.TicTacToe.strategies.botWinningStrategies;

import com.TicTacToe.models.Board;
import com.TicTacToe.models.Cell;
import com.TicTacToe.models.CellState;
import com.TicTacToe.models.Move;

import java.util.List;

public class EasyBotWinningStrategy implements BotwinningStrategy{
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> cells : board.getBoard()){
            for(Cell cell : cells){
                if(cell.getCellState() == CellState.EMPTY){
                    return new Move(null,cell);
                }
            }
        }
        return null;
    }
}
