package com.main.test;

import com.main.MainApplication;
import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class ConfigurationScreenTest extends ApplicationTest {
    private MainApplication app;
    private DataController dataController;
    private Pane mainNode;
    private Stage mainstage;

    @Override
    public void start(Stage stage) throws Exception {
        mainNode = (Pane) FXMLLoader.load(
                MainApplication.class.getResource(
                        GameSettingDataMap.getFileName(GameScreenType.CONFIG_SCREEN)
                )
        );
        mainstage = stage;
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {
        dataController = MainApplication.getDataController();
    }

    @Test
    public void testPlayerNameValidityWithBlank() {
        dataController.setPlayerName(" "); //test invalid player name input
        assertFalse(dataController.isPlayerNameValid(dataController.getPlayerName()));
        assertFalse(dataController.isAbleToGoGameScreen());
    }


}
