package com.main.test;

import com.main.MainApplication;
import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.tower.TowerData;
import com.main.game.entity.tower.TowerMenu;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class tests extends ApplicationTest{
    private MainApplication app;
    private DataController dataController;
    private Parent mainNode;
    private Stage mainstage;

    private int diff = 0;



    //private GameLevelType gameLevelType;
    @Override
    public void start(Stage stage) throws Exception {
//        mainNode = (Parent) FXMLLoader.load(
//                MainApplication.class.getResource(
//                        GameSettingDataMap.getFileName(GameScreenType.GAME_SCREEN)
//                )
//        );
//        mainstage = stage;
//        stage.setScene(new Scene(mainNode));
//        stage.show();
//        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testCostForGameLevelEasy() {
        if ((GameSettingDataMap.getTowerInitialCost(GameLevelType.EASY)) != GameSettingDataMap.getTowerInitialCost(GameLevelType.NORMAL)) {
            diff = 1;
        }
        assertSame(1, diff);
    }

    @Test
    public void testCostForGameLevelNormal() {
        if ((GameSettingDataMap.getTowerInitialCost(GameLevelType.NORMAL)) != GameSettingDataMap.getTowerInitialCost(GameLevelType.HARD)) {
            diff = 1;
        }
        assertSame(1, diff);
    }
    @Test
    public void testCostForGameLevelHard() {
        if ((GameSettingDataMap.getTowerInitialCost(GameLevelType.EASY)) != GameSettingDataMap.getTowerInitialCost(GameLevelType.HARD)) {
            diff = 1;
        }
        assertSame(1, diff);
    }

}
