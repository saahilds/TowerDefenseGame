package com.test;

import com.game.Enemy;
import com.game.GameSceneWrapper;
import com.main.MainApplication;
import com.main.ScreensController;
import com.main.game.DataController;
import com.main.game.GameFlowController;
import com.main.game.components.gameScreen.GameFlowControllerComponent;
import com.main.game.components.gameScreen.TowerMenuComponent;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.tower.TowerData;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CombatTest {
    private VBox gameFlowControllerEl;
    private GameFlowController gF;

    private MainApplication app;
    private DataController dataController;
    private Pane mainNode;
    private Stage mainstage;

    @Override
    public void start(Stage stage) throws Exception {
        mainNode = (Pane) FXMLLoader.load(
                MainApplication.class.getResource(
                        GameSettingDataMap.getFileName(GameScreenType.GAME_SCREEN)
                )
        );
        mainstage = stage;
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp() {
        dataController = MainApplication.getDataController();
    }

    @Test
    public void testUniqueTower() {
       TowerMenuComponent easy = new TowerMenuComponent(GameLevelType.EASY);
       boolean isNotUnique = false;
       ArrayList<TowerData> towers = easy.getTowerDataItemList();

       for (int i = 0; i < towers.size() - 1; i ++) {
           if (towers.get(i).getDps() == towers.get(i + 1).getDps() //testing if adjacent towers have the same attributes
                   && towers.get(i).getHealth() == towers.get(i + 1).getHealth()) {
               isNotUnique = true;
           }
       }
       if (towers.get(0).getDps() == towers.get(2).getDps() //testing if first and last towers have same attributes
               && towers.get(0).getHealth() == towers.get(2).getHealth()) {
           isNotUnique = true;
       }
       assertFalse(isNotUnique);
    }

    @Test
    public void testUniqueEnemy() {
        GameSceneWrapper g = new GameSceneWrapper(mainstage, new AnchorPane(mainNode), new Scene(mainNode));
        g.spawnEnemy();
        g.spawnEnemy();

        ArrayList<Enemy> enemies = g.getEnemies();
        assertFalse(enemies.get(0).getDamage() == enemies.get(1).getDamage());

    }
}
