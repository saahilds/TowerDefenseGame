package com.main.test;

import com.main.MainApplication;
import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.EntityWithHealth;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class GameScreenTest extends ApplicationTest {
    private MainApplication app;
    private DataController dataController;
    private Parent mainNode;
    private Stage mainstage;

    @Override
    public void start(Stage stage) throws Exception {
        mainNode = (Parent) FXMLLoader.load(
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
    public void setUp() throws Exception {
    }

    @Test
    public void testEnemyInitialization() {
        GameLevelType level = GameLevelType.EASY;
        EntityWithHealth enemyEntity = from(mainNode).lookup("#enemyEntity").query();
        assertNotNull(enemyEntity);
        assertSame(
                (int) enemyEntity.getMaxHP(),
                GameSettingDataMap.getStartingMonumentHealth(level)
        );
    }
}
