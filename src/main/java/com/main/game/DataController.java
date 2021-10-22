package com.main.game;

import com.main.game.data.GameSettingDataMap;
import com.main.model.GameLevelType;

import java.util.Objects;
import java.util.Observable;

public class DataController {
    private GameLevelType gameLevel = GameLevelType.EASY;
    private String playerName = "";
    private Integer gameMoney = 0;
    private Integer enemyMonumentHealth = 0;

    public GameMoneyObservable getGameMoneyObservable() {
        return gameMoneyObservable;
    }

    public void setGameMoneyObservable(GameMoneyObservable gameMoneyObservable) {
        this.gameMoneyObservable = gameMoneyObservable;
    }

    private GameMoneyObservable gameMoneyObservable = new GameMoneyObservable();

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
        System.out.println("DC setGameMoney: " + gameMoney);
        this.gameMoney = gameMoney;
        gameMoneyObservable.setGameMoney(gameMoney);
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

    public class GameMoneyObservable extends Observable {
        private Integer gameMoney;

        public void integerObservable(Integer gameMoney) {
            this.gameMoney = gameMoney;
        }

        public Integer getGameMoney() {
            return gameMoney;
        }

        public void setGameMoney(Integer gameMoney) {
            System.out.println("GM OBSERVABLE setGameMoney: " + gameMoney);
            this.gameMoney = gameMoney;
            setChanged();
            notifyObservers(gameMoney);
        }
    }
}
