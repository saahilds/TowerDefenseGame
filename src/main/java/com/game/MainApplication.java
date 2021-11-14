package com.game;

import com.game.config.Config;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private static Stage primaryStage;
    private AnchorPane root;
    private Scene scene;
    private GameSceneWrapper gameSceneWrapper;

    @Override
    public void start(Stage primaryStage) {
        root = new AnchorPane();
        scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        gameSceneWrapper = new GameSceneWrapper(primaryStage, root, scene);

        primaryStage.setTitle("v2");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
