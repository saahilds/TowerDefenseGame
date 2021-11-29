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

    private SceneWrapper sceneWrapper;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.root = new AnchorPane();
        this.scene = new Scene(this.root, Config.STAGE_WIDTH,
                Config.STAGE_HEIGHT, Color.GHOSTWHITE);

        this.stage.setTitle("Dino Defense");
        this.stage.setResizable(false);

        sceneWrapper = new SceneWrapper();
        sceneWrapper.initWelcomeScene(stage, root);
        // FIXME: 2021/11/28
//        sceneWrapper.initGameScene(stage, root);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
