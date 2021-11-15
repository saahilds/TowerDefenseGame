package com.game;

import com.main.model.GameLevelType;
import com.main.model.GameScreenType;

import java.util.HashMap;

public class GameSettingDataMap {
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

    public static HashMap<GameLevelType, String> gameLevelTextMap = new HashMap<>() {
        {
            put(GameLevelType.EASY, "EASY");
            put(GameLevelType.NORMAL, "NORMAL");
            put(GameLevelType.HARD, "HARD");
        }
    };


    private static HashMap<GameLevelType, Integer> startingMonumentHealthMap  = new HashMap<>() {
        {
            put(GameLevelType.EASY, 1000);
            put(GameLevelType.NORMAL, 500);
            put(GameLevelType.HARD, 100);
        }
    };

    public static Integer getStartingMonumentHealth(GameLevelType gameLevelType) {
        return startingMonumentHealthMap.get(gameLevelType);
    }

    private static HashMap<GameLevelType, Integer> towerInitialCostMap = new HashMap<>() {
        {
            put(GameLevelType.EASY, 25);
            put(GameLevelType.NORMAL, 50);
            put(GameLevelType.HARD, 100);
        }
    };

    public static Integer getTowerInitialCost(GameLevelType gameLevelType) {
        return towerInitialCostMap.get(gameLevelType);
    }
}
