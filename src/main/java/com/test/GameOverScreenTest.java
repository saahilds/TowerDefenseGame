package com.main.test;
import com.main.MainApplication;
import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

public class GameOverScreenTest extends ApplicationTest {
    private DataController dataController;
    private Pane mainNode;
    private Button button;

    @Override
    public void start(Stage stage) throws Exception {
        mainNode = (Pane) FXMLLoader.load(
                MainApplication.class.getResource(
                        GameSettingDataMap.getFileName(GameScreenType.GAME_OVER_SCREEN)
                )
        );
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {
        dataController = MainApplication.getDataController();
    }

    @Test
    public void testGameOverScreenInit() { // check if gameOverScreen is initialized
        assertNotNull(mainNode);
    }
}

  /*  @Test
    public void restartGame() { // check if restartgame and exitgame buttons exists
        assertNotNull(clickOn("#restartGame"));
        assertNotNull(clickOn("#exitGame"));
    }
}*/

//screensController.setScreen(GameScreenType.GAME_OVER_SCREEN);
//Button button = from(mainNode).lookup("restartGame").query();
//assertEquals(button.getText(), "Restart Game");
//ScreensController screensController = new ScreensController();

