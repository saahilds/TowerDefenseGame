package com.test;

import com.game.GameSceneWrapper;
import com.game.config.Config;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

public class ProjectileTest extends ApplicationTest {
    private AnchorPane root;
    private Scene scene;
    private GameSceneWrapper gameSceneWrapper;

    BackgroundImage myBI = new BackgroundImage(
            new Image(
                    getClass().getResourceAsStream("/com/game/bg5.gif"),
                    1100,
                    1100, false, false),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            new BackgroundPosition(Side.LEFT, 0.5D, true, Side.BOTTOM, 0.0D, true),
            BackgroundSize.DEFAULT
    );

    @Override
    public void start(Stage stage) throws Exception {
        root = new AnchorPane();
        scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        root.setBackground(new Background(myBI));
        gameSceneWrapper = new GameSceneWrapper(stage, root, scene);
        stage.show();
    }

    @Test
    public void testReducedHPEasy() {
        if (gameSceneWrapper.getCounter() == 0) {
            assertTrue(gameSceneWrapper.getGameMoney() == 0);
        }
    }

}
