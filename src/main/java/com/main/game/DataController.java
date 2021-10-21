package com.main.game;

import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.Tower;
import com.main.model.GameLevelType;
//import com.main.model.GameScreenType;

import java.util.Objects;

public class DataController {
    private GameLevelType gameLevel;
    private String playerName = "";
    private Integer gameMoney = 0;
    private Integer enemyMonumentHealth = 0;

    // GETTER / SETTER
    public GameLevelType getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(GameLevelType gameLevel) {
        if (gameLevel != null) {
            this.gameMoney = GameSettingDataMap.getStartingMoney(gameLevel);
            this.enemyMonumentHealth = GameSettingDataMap.getStartingMonumentHealth(gameLevel);
            this.gameLevel = gameLevel;
        }
    }

    //GETTER for the playerName
    public String getPlayerName() {
        return playerName;
    }

    //GETTER for the playerName
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public Integer getGameMoney() {
        return gameMoney;
    }

    public void setGameMoney(Integer gameMoney) {
        this.gameMoney = gameMoney;
    }

    public Integer getEnemyMonumentHealth() {
        return enemyMonumentHealth;
    }

    public void setEnemyMonumentHealth(Integer enemyMonumentHealth) {
        this.enemyMonumentHealth = enemyMonumentHealth;
    }

    public boolean isPlayerNameValid(String nameInText) {
        return !(nameInText == null
                || Objects.equals(nameInText, "")
                || nameInText.trim().isEmpty());
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
