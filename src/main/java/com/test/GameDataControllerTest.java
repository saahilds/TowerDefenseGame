package com.main.test;

import com.main.config.Config;
import com.main.game.DataController;
import com.main.game.GameDataController;
import com.main.game.GameFlowController;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.tower.TowerData;
import com.main.game.entity.tower.TowerEntity;
import com.main.game.gamePane.GamePaneWrapper;
import com.main.model.GameLevelType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameDataControllerTest extends ApplicationTest {

    private GamePaneWrapper gamePaneWrapper;
    private GameDataController gameDataController;
    private DataController dataController;
    private AnchorPane pane;

    private Stage mainstage;

    @Override
    public void start(Stage stage) throws Exception {
        GameFlowController gameFlowController = new GameFlowController();
        pane = new AnchorPane();
        gamePaneWrapper = new GamePaneWrapper(
                pane, Config.STAGE_WIDTH - Config.LEFT_TOOLBAR_WIDTH,
                Config.STAGE_HEIGHT - Config.GNB_TOP_HEIGHT,
                Config.UNIT, Config.UNIT,
                gameFlowController
        );
        dataController = new DataController();
        gameDataController = new GameDataController(
                gamePaneWrapper,
                dataController,
                gameFlowController,
                GameLevelType.EASY
        );
        mainstage = stage;
        Scene scene = new Scene(pane, Config.STAGE_WIDTH, Config.STAGE_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testGamePaneInit() {
        assertNotNull(gamePaneWrapper.getPane());
    }

    @Test
    public void testGameDataControllerInitNoSelectedTower() {
        assertNull(gameDataController.getSelectedTower());
    }

    @Test
    public void testPositionMap() {
        ArrayList<Node> towerEntities = new ArrayList<>(10);

        int yidx = 1;

        for (int i = 0; i < 10; i++) {
            TowerData catapultTower = new TowerData(
                    "catapult_tower",
                    "Catapult Tower",
                    "Catapult Tower Desc",
                    GameSettingDataMap.getTowerInitialCost(GameLevelType.EASY),
                    75,
                    150.0,
                    ""
            );

            TowerEntity towerEntity = new TowerEntity(catapultTower);
            towerEntity.setId("testTower" + i);
            gamePaneWrapper.getPosMap().setAtIdx(i, yidx, towerEntity);
            towerEntities.add(towerEntity);
        }

        for (int i = 0; i < 10; i++) {
            Node a = gamePaneWrapper.getPosMap().getAtIdx(i, yidx);
            Node b = towerEntities.get(i);
            assertEquals(a, b);
        }
    }
}
