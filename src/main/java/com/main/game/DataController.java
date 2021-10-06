package com.main.game;

import com.main.game.data.GameSettingDataMap;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;

import java.util.Objects;

public class DataController {
    private GameLevelType gameLevel;
    private String playerName;
    private Integer gameMoney;

    // GETTER / SETTER
    public GameLevelType getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(GameLevelType gameLevel) {
        if (gameLevel != null) {
            this.gameMoney = GameSettingDataMap.getStartingMoney(gameLevel);
            this.gameLevel = gameLevel;
        }
    }

    /**
     * GETTER for the playerName
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * GETTER for the playerName
     * @param playerName
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public Integer getGameMoney() {
        return gameMoney;
    }

    public void setGameMoney(Integer gameMoney) {
        this.gameMoney = gameMoney;
    }

    public boolean isPlayerNameValid(String nameInText) {
        return !(nameInText == null || Objects.equals(nameInText, "") || nameInText.contains(" "));
    }

    public boolean isGameLevelValid(GameLevelType gameLevelType) {
        return gameLevelType != null;
    }

    public boolean isAbleToGoGameScreen() {
        boolean playerNameValidity = this.isPlayerNameValid(this.playerName);
        boolean gameLevelValidity = this.isGameLevelValid(this.gameLevel);
        return playerNameValidity && gameLevelValidity;
    }
}
