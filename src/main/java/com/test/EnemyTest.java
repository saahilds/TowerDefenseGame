package com.main.test;


import com.main.MainApplication;
import com.main.ScreensController;
import com.main.game.DataController;
import com.main.game.GameFlowController;
import com.main.game.components.gameScreen.GameFlowControllerComponent;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

public class EnemyTest extends ApplicationTest {
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
    public void gameStartButtonExists() {
        gameFlowControllerEl = new VBox();
        gF = new GameFlowController();
        GameFlowControllerComponent gFC = new GameFlowControllerComponent(gameFlowControllerEl, gF);
        boolean b = false;
        //checking if button is created when gFC is created
        if (gameFlowControllerEl.getChildren() != null) {
            b = true;
        }
        assertTrue(b);
    }

    @Test
    public void gameStartButtonTest() {
        gameFlowControllerEl = new VBox();
        gF = new GameFlowController();
        GameFlowControllerComponent gFC = new GameFlowControllerComponent(gameFlowControllerEl, gF);
        //making sure game hasn't started without mouse click
        assertFalse(gFC.getIsGameStarted());

    }

    @Test
    public void testClockStart() {
        GameFlowController gameFlowController = new GameFlowController();
        assertFalse(gameFlowController.isClockStarted());
        gameFlowController.setClockStarted(true);
        assertTrue(gameFlowController.isClockStarted());
    }

    @Test
    public void testGameOverScreen() {
        ScreensController screensController = new ScreensController();
        Node screen = new Node() {
        };
        screensController.addScreen(GameScreenType.GAME_OVER_SCREEN, screen);
        screensController.setScreen(GameScreenType.GAME_OVER_SCREEN);

        assertNotNull(screensController.getScreen(GameScreenType.GAME_OVER_SCREEN));
    }
}