package com.game;

import com.game.components.gameScene.TowerData;
import com.game.model.GameLevelType;
import com.game.model.TowerType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameSettingDataMap {
    private static HashMap<GameLevelType, Integer> startingMoneyMap = new HashMap<>() {
        {
            put(GameLevelType.EASY, 1000);
            put(GameLevelType.NORMAL, 500);
            put(GameLevelType.HARD, 100);
        }
    };

    public static Integer getStartingMoney(GameLevelType gameLevelType) {
        return startingMoneyMap.get(gameLevelType);
    }

    private static HashMap<GameLevelType, String> gameLevelTextMap = new HashMap<>() {
        {
            put(GameLevelType.EASY, "EASY");
            put(GameLevelType.NORMAL, "NORMAL");
            put(GameLevelType.HARD, "HARD");
        }
    };

    public static HashMap<GameLevelType, String> getGameLevelTextMap() {
        return gameLevelTextMap;
    }

    private static HashMap<GameLevelType, Integer> startingMonumentHealthMap = new HashMap<>() {
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
