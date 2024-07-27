package com.TicTacToe.factory;

import com.TicTacToe.models.BotDifficultyLevel;
import com.TicTacToe.strategies.botWinningStrategies.BotwinningStrategy;
import com.TicTacToe.strategies.botWinningStrategies.EasyBotWinningStrategy;
import com.TicTacToe.strategies.botWinningStrategies.HardBotWinningStrategy;
import com.TicTacToe.strategies.botWinningStrategies.MediumBotWinningStrategy;

public class BotPlayingStrategyFactory {
    public static BotwinningStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotWinningStrategy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotWinningStrategy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.HARD)) {
            return new HardBotWinningStrategy();
        }
        return null;
    }
}
