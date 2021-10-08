package com.main.game.data;

import com.main.model.GameLevelType;
import com.main.model.GameScreenType;

import java.util.HashMap;

public class GameSettingDataMap {
    private static String welcomeFile = "main.fxml";

    public static String getWelcomeFile() {
        return welcomeFile;
    }

    private static String configScreenFile = "initial-screen.fxml";

    public static String getConfigScreenFile() {
        return configScreenFile;
    }

    private static String gameScreenFile = "game-screen.fxml";

    public static String getGameScreenFile() {
        return gameScreenFile;
    }

    private static HashMap<GameScreenType, String> screenFileMap = new HashMap<>() {
        {
            put(GameScreenType.WELCOME_SCREEN, welcomeFile);
            put(GameScreenType.CONFIG_SCREEN, configScreenFile);
            put(GameScreenType.GAME_SCREEN, gameScreenFile);
        }
    };

    public static String getFileName(GameScreenType gameScreenType) {
        return screenFileMap.get(gameScreenType);
    }

    private static HashMap<GameLevelType, Integer> startingMoneyMap  = new HashMap<>() {
        {
            put(GameLevelType.EASY, 1000);
            put(GameLevelType.NORMAL, 500);
            put(GameLevelType.HARD, 100);
        }
    };

    public static Integer getStartingMoney(GameLevelType gameLevelType) {
        return startingMoneyMap.get(gameLevelType);
    }
}
