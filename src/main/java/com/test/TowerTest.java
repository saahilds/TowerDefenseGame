package com.test;

import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.tower.TowerData;
import com.main.game.entity.tower.TowerEntity;
import com.main.game.components.gameScreen.TowerMenuComponent;
import com.main.model.GameLevelType;
import com.main.model.TowerEntityStatusType;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class TowerTest extends ApplicationTest {
    private DataController dataController;

    private int diff = 0;

    @Override
    public void start(Stage stage) throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNecessaryFunds() {
        dataController = new DataController();
        dataController.setGameLevel(GameLevelType.EASY);
        TowerMenuComponent t = new TowerMenuComponent(GameLevelType.EASY);

        assertTrue(dataController.getGameMoney() > t.getTowerDataItemList().get(0).getCost());
    }

    @Test
    public void testNotEnoughFunds() {
        dataController = new DataController();
        dataController.setGameLevel(GameLevelType.HARD);
        TowerMenuComponent t = new TowerMenuComponent(GameLevelType.HARD);

        assertFalse(dataController.getGameMoney() > t.getTowerDataItemList().get(0).getCost());
    }

    @Test
    public void testTowerCreation() {
        GameLevelType gameLevel = GameLevelType.EASY;
        TowerData techTower = new TowerData(
                "tech_tower",
                "Tech Tower",
                "Iconic Building of Georgia Tech",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                50,
                100.0,
                "/com/main/TechTower01.png"
        );
        assertTrue(techTower instanceof TowerData);
    }

    @Test
    public void testTowerEntityOnMap() {
        GameLevelType gameLevel = GameLevelType.EASY;
        TowerData techTower = new TowerData(
                "tech_tower",
                "Tech Tower",
                "Iconic Building of Georgia Tech",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                50,
                100.0,
                ""
        );
        TowerEntity techTowerEntity = new TowerEntity(techTower, TowerEntityStatusType.REGISTERED);
        assertEquals(techTowerEntity.getTowerEntityStatus(), TowerEntityStatusType.REGISTERED);
    }

    @Test
    public void testNameValidity() {
        TowerMenuComponent towerMenu = new TowerMenuComponent(GameLevelType.EASY);
        for (int i = 0; i < towerMenu.getTowerDataItemList().size(); i++) {
            TowerData testTower = towerMenu.getTowerDataItemList().get(i);
            String[] nameArray = {"Tech Tower", "Westin", "Pencil Building", "Catapult Tower"};
            assertSame(testTower.getName(), nameArray[i]);
        }
    }

    @Test
    public void testCostForGameLevelEasy() {
        if ((GameSettingDataMap.getTowerInitialCost(GameLevelType.EASY))
                != GameSettingDataMap.getTowerInitialCost(GameLevelType.NORMAL)) {
            diff = 1;
        }
        assertSame(1, diff);
    }

    @Test
    public void testCostForGameLevelNormal() {
        if ((GameSettingDataMap.getTowerInitialCost(GameLevelType.NORMAL))
                != GameSettingDataMap.getTowerInitialCost(GameLevelType.HARD)) {
            diff = 1;
        }
        assertSame(1, diff);
    }
    @Test
    public void testCostForGameLevelHard() {
        if ((GameSettingDataMap.getTowerInitialCost(GameLevelType.EASY))
                != GameSettingDataMap.getTowerInitialCost(GameLevelType.HARD)) {
            diff = 1;
        }
        assertSame(1, diff);
    }

}


