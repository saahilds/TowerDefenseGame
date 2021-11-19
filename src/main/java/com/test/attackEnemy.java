package com.test;

import com.game.*;
import com.game.config.Config;
import com.game.model.GameLevelType;
import com.game.model.TowerType;
import com.main.game.GameDataController;
import com.main.game.entity.EnemyEntity;
import com.main.game.entity.PlayerEntity;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Iterator;

import static org.junit.Assert.*;

public class attackEnemy extends ApplicationTest {
    private AnchorPane root = new AnchorPane();
    private Scene scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
    private GameSceneWrapper gameSceneWrapper;
    private GameDataController gameDataController;
    private Rectangle rectangle;

    BackgroundImage myBI = new BackgroundImage(
            new Image(
                    getClass().getResourceAsStream("/com/game/bg5.gif"),
                    1100,
                    1100, false, false),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            new BackgroundPosition(Side.LEFT, 0.5D, true, Side.BOTTOM, 0.0D, true),
            BackgroundSize.DEFAULT
    );

    @Override
    public void start(Stage stage) throws Exception {
        //root = new AnchorPane();
        //scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        SceneWrapper.setGameLevel(GameLevelType.EASY);
        root.setBackground(new Background(myBI));
        gameSceneWrapper = new GameSceneWrapper(stage, root, scene);
        stage.show();
    }

    //A single tower can shoot
    // some sort of projectile at enemies
    @Test
    public void towerCanShootTest() {
        Enemy enemy = new Enemy(1, 1, 1, 1,1);
        Tower tower = new Tower(rectangle, TowerType.TYPE_A, 20, 20);
        //tower.getProjectile();
        float before = enemy.getCurrHP();
        gameSceneWrapper.loopHandler();
        float after = enemy.getCurrHP();
        assertTrue (before > after);
    }

    @Test
    public void towerCanShootTest2() {
        Enemy enemy = new Enemy(2, 2, 2, 2,2);
        Tower tower = new Tower(rectangle, TowerType.TYPE_B, 20, 20);
        float before = enemy.getCurrHP();
        gameSceneWrapper.loopHandler();
        float after = enemy.getCurrHP();
        assertTrue (before > after);
    }

    @Test
    public void towerCanShootTest3() {
        Enemy enemy = new Enemy(2, 2, 2, 2,2);
        Tower tower = new Tower(rectangle, TowerType.TYPE_C, 20, 20);
        float before = enemy.getCurrHP();
        gameSceneWrapper.loopHandler();
        float after = enemy.getCurrHP();
        assertTrue (before > after);
    }




}
