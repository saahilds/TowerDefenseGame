package com.main.game;

import com.main.config.Config;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.tower.TowerData;
import com.main.game.entity.tower.TowerEntity;
import com.main.game.entity.EntityWithHealth;
import com.main.game.path.PathBlock;
import com.main.game.path.TexturePathBlock;
import com.main.model.GameLevelType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameDataController {
    private GameLevelType gameLevel = GameLevelType.EASY;
    private Integer gameMoney = 0;
    private GamePaneWrapper gamePaneWrapper;

    private TowerData selectedTower;

    public GameDataController(
            GamePaneWrapper gamePaneWrapper,
            GameLevelType gameLevel
    ) {
        this.gamePaneWrapper = gamePaneWrapper;
        this.gameLevel = gameLevel;

        this.generateSimplePath();
        this.initGameScenario();
        this.initMouseEventHandlerSetting();
    }

    public void initGameScenario() {
        Rectangle b = new Rectangle(32, 32, Color.RED);
        Rectangle c = new Rectangle(32, 32, Color.RED);
        System.out.println(gamePaneWrapper.getWidthCapacity()
                + "|" + gamePaneWrapper.getHeightCapacity());
        int maxXidx = gamePaneWrapper.getMaxXidx();
        int maxYidx = gamePaneWrapper.getMaxYidx();

        this.generateSimplePath();

        int vCenterIdx = (int) Math.floor(gamePaneWrapper.getMaxYidx() / 2);
        Integer startingMonumentHealth;
        if (this.gameLevel == null) {
            startingMonumentHealth = 100;
        } else {
            startingMonumentHealth = GameSettingDataMap.getStartingMonumentHealth(this.gameLevel);
        }

        EntityWithHealth enemy = new EntityWithHealth(
                32,
                32,
                startingMonumentHealth,
                startingMonumentHealth);
        enemy.setFillWithImageSrc("/com/main/skeleton_01.png");
        enemy.setId("enemyEntity");

        EntityWithHealth player = new EntityWithHealth(32, 32, 100, 80);
        player.setFillWithImageSrc("/com/main/steve_01.jpeg");
        player.setId("playerEntity");

        TowerEntity tower = new TowerEntity();
        tower.setFillWithImageSrc("/com/main/catapult.png");
        player.setId("catapultEntity");

        this.gamePaneWrapper.addNodeWithXidxYidx(0, vCenterIdx, player);
        this.gamePaneWrapper.addNodeWithXidxYidx(maxXidx - 1, vCenterIdx, enemy);
        this.gamePaneWrapper.addNodeWithXidxYidx(0, maxYidx, c);
        this.gamePaneWrapper.addNodeWithXidxYidx(1, 1, tower);
    }


    public void generateSimplePath() {
        int vCenterIdx = (int) Math.floor(gamePaneWrapper.getMaxYidx() / 2);
        for (int xIdx = 0; xIdx < gamePaneWrapper.getMaxXidx() + 1; xIdx++) {
            Image textureImage = new Image(
                    getClass().getResourceAsStream("/com/main/grass_1.png")
            );
            ImagePattern textureImagePattern = new ImagePattern(textureImage);
            PathBlock pathBlock =
                    new TexturePathBlock(Config.UNIT, Config.UNIT, xIdx, textureImagePattern);
            this.gamePaneWrapper.addNodeWithXidxYidx(xIdx, vCenterIdx, pathBlock);
        }
    }

    public void initMouseEventHandlerSetting() {
        gamePaneWrapper.setOnClickMouseEventHandler(mouseEvent -> {
            System.out.println(mouseEvent);
            handleClickMouseEvent(mouseEvent);
        });
    }

    private void handleClickMouseEvent(MouseEvent mouseEvent) {
        if (mouseEvent != null) {
            System.out.println("========== MOUSE CLICKED ==========");
            System.out.println(mouseEvent);
            System.out.println("========== MOUSE CLICKED ==========");
        }
    }

}
