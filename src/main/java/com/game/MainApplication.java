package com.game;

import com.game.config.Config;
//import com.main.model.GameLevelType;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//import java.util.ArrayList;
//import java.util.HashMap;

public class MainApplication extends Application {
    private static Stage stage;
    private AnchorPane root;
    private Scene scene;

    private BackgroundImage myBI = new BackgroundImage(
            new Image(
                    getClass().getResourceAsStream("/com/game/bg5.gif"),
                    1100,
                    1100, false, false),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            new BackgroundPosition(Side.LEFT, 0.5D, true, Side.BOTTOM, 0.0D, true),
            BackgroundSize.DEFAULT
    );

    private SceneWrapper sceneWrapper;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.root = new AnchorPane();
        this.scene = new Scene(this.root, Config.STAGE_WIDTH,
                Config.STAGE_HEIGHT, Color.GHOSTWHITE);

        this.stage.setTitle("v2");
        this.stage.setResizable(false);

        sceneWrapper = new SceneWrapper();
        sceneWrapper.initWelcomeScene(stage, root);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
