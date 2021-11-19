//package com.test;
//
//import com.game.GameSceneWrapper;
//import com.game.GameSettingDataMap;
//import com.game.SceneWrapper;
//import com.game.config.Config;
//import com.game.model.GameLevelType;
//import javafx.geometry.Side;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//
//import static org.junit.Assert.assertTrue;
//
//public class ProjectileTest extends ApplicationTest {
//    private AnchorPane root;
//    private Scene scene;
//    private GameSceneWrapper gameSceneWrapper;
//
//    BackgroundImage myBI = new BackgroundImage(
//            new Image(
//                    getClass().getResourceAsStream("/com/game/bg5.gif"),
//                    1100,
//                    1100, false, false),
//            BackgroundRepeat.NO_REPEAT,
//            BackgroundRepeat.NO_REPEAT,
//            new BackgroundPosition(Side.LEFT, 0.5D, true, Side.BOTTOM, 0.0D, true),
//            BackgroundSize.DEFAULT
//    );
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        root = new AnchorPane();
//        scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
//        SceneWrapper.setGameLevel(GameLevelType.EASY);
//        root.setBackground(new Background(myBI));
//        gameSceneWrapper = new GameSceneWrapper(stage, root, scene);
//        stage.show();
//    }
//
//    @Test
//    public void testMoneyIncrease() {
//        int currMoney = 0;
//        int counter = 0;
//        while (counter <= gameSceneWrapper.getGameMoneyIncrementSpeed() * 10) {
//            counter++;
//            if (gameSceneWrapper.getCounter() == 0) {
//                currMoney = gameSceneWrapper.getGameMoney();
//                assertTrue(gameSceneWrapper.getGameMoney() == GameSettingDataMap.getStartingMoney(GameLevelType.EASY));
//            } else if (gameSceneWrapper.getCounter() % gameSceneWrapper.getGameMoneyIncrementSpeed() == 0) {
//                assertTrue(currMoney < gameSceneWrapper.getGameMoney());
//            }
//        }
//    }
//
//    @Test
//    public void projectTimeTest1() {
//        gameSceneWrapper.onEnter();
//        gameSceneWrapper.setCounter(50);
//        gameSceneWrapper.triggerTowerShot();
//        assertTrue(gameSceneWrapper.getProjectiles().size() == 1);
//    }
//
//    @Test
//    public void projectTimeTest2() {
//        gameSceneWrapper.onEnter();
//        gameSceneWrapper.setCounter(50);
//        gameSceneWrapper.triggerTowerShot();
//        gameSceneWrapper.setCounter(100);
//        gameSceneWrapper.triggerTowerShot();
//        assertTrue(gameSceneWrapper.getProjectiles().size() == 2);
//    }
//
//    @Test
//    public void projectTimeTest3() {
//        gameSceneWrapper.onEnter();
//        gameSceneWrapper.setCounter(50);
//        gameSceneWrapper.triggerTowerShot();
//        gameSceneWrapper.setCounter(100);
//        gameSceneWrapper.triggerTowerShot();
//        gameSceneWrapper.setCounter(150);
//        gameSceneWrapper.triggerTowerShot();
//        assertTrue(gameSceneWrapper.getProjectiles().size() == 3);
//    }
//
//    @Test
//    public void projectTimeGameMoneyIncrementOnlyOnSpeed() {
//        int moneySpeed = gameSceneWrapper.getGameMoneyIncrementSpeed();
//        gameSceneWrapper.setCounter(moneySpeed);
//        gameSceneWrapper.handleGameOver();
//        int prevMoney = 0;
//        for (int i = 0; i <= 500; i++) {
//            gameSceneWrapper.loopHandler();
//            if (i % moneySpeed != 0) {
//                assertTrue(prevMoney == gameSceneWrapper.getGameMoney());
//            }
//            prevMoney = gameSceneWrapper.getGameMoney();
//        }
//    }
//
//    @Test
//    public void projectTimeGameMoneyIncrement() {
//        int moneySpeed = gameSceneWrapper.getGameMoneyIncrementSpeed();
//        gameSceneWrapper.setCounter(moneySpeed);
//        gameSceneWrapper.handleGameOver();
//        int prevMoney = 0;
//        for (int i = 0; i <= moneySpeed; i++) {
//            gameSceneWrapper.loopHandler();
//            if (i % moneySpeed == 0) {
//                assertTrue(prevMoney <= gameSceneWrapper.getGameMoney());
//            }
//            prevMoney = gameSceneWrapper.getGameMoney();
//        }
//    }
//
//
//}
