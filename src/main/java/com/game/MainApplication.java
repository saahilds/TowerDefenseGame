package com.game;

import com.game.config.Config;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class MainApplication extends Application {
    private static Stage primaryStage;
    private AnchorPane root;
    private Scene scene;

    private WelcomeSceneWrapper welcomeSceneWrapper;
    private GameSceneWrapper gameSceneWrapper;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new AnchorPane();
        this.scene = new Scene(this.root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);

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
        root.setBackground(new Background(myBI));
        root.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.primaryStage.setTitle("v2");
        this.primaryStage.setResizable(false);

        initWelcomeScene();
//        initGameScene();

        this.primaryStage.show();
    }

    public void initWelcomeScene() {
        welcomeSceneWrapper = new WelcomeSceneWrapper(primaryStage, root, scene);
        primaryStage.setScene(scene);
    }

    public void initGameScene() {
        gameSceneWrapper = new GameSceneWrapper(primaryStage, root, scene);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
