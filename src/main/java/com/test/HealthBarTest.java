package com.main.test;

//import com.main.GameScreenController;
//import com.main.ScreensController;
import com.main.config.Config;
import com.main.game.DataController;
import com.main.game.GameDataController;
import com.main.game.GameFlowController;
//import com.main.game.common.UpdateData;
import com.main.game.data.GameSettingDataMap;
//import com.main.game.entity.HealthBar;
import com.main.game.entity.PlayerEntity;
import com.main.game.gamePane.GamePaneWrapper;
import com.main.model.GameLevelType;
//import com.main.model.GameScreenType;
//import com.main.model.UpdateDataTypeType;
//import io.reactivex.Observable;
//import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class HealthBarTest extends ApplicationTest {

    private GamePaneWrapper gamePaneWrapper;
    private GameDataController gameDataController;
    private DataController dataController;
    private AnchorPane pane;
    private GameFlowController gameFlowController;

    private Stage mainstage;

    @Override
    public void start(Stage stage) throws Exception {
        gameFlowController = new GameFlowController();
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
                GameLevelType.HARD
        );
        mainstage = stage;
        Scene scene = new Scene(pane, Config.STAGE_WIDTH, Config.STAGE_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testReducedHPEasy() {
        PlayerEntity player = gameDataController.getPlayer();
        float before = GameSettingDataMap.getStartingMonumentHealth(GameLevelType.EASY);
        float after = player.applyHpChange(-20);
        assertTrue(before > after);
    }

    @Test
    public void testReducedHPNormal() {
        PlayerEntity player = gameDataController.getPlayer();
        float before = GameSettingDataMap.getStartingMonumentHealth(GameLevelType.NORMAL);
        float after = player.applyHpChange(-20);
        assertTrue(before > after);
    }

    @Test
    public void testReducedHPHard() {
        PlayerEntity player = gameDataController.getPlayer();
        float before = GameSettingDataMap.getStartingMonumentHealth(GameLevelType.HARD);
        float after = player.applyHpChange(-20);
        assertTrue(before > after);
    }


}
