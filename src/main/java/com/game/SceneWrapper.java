package com.game;

import com.game.config.Config;
import com.game.model.GameLevelType;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SceneWrapper {
    private static String username;

    public static String getUsername() {
        return SceneWrapper.username;
    }

    public static void setUsername(String username) {
        SceneWrapper.username = username;
    }

    private static GameLevelType gameLevel = GameLevelType.EASY;

    public static GameLevelType getGameLevel() {
        return SceneWrapper.gameLevel;
    }

    public static void setGameLevel(GameLevelType gameLevel) {
        SceneWrapper.gameLevel = gameLevel;
    }

    public static ArrayList<GameLevelType> getGameLevelTypeArr() {
        return SceneWrapper.gameLevelTypeArr;
    }

    private static ArrayList<GameLevelType> gameLevelTypeArr = new ArrayList<GameLevelType>() {{
        add(GameLevelType.EASY);
        add(GameLevelType.NORMAL);
        add(GameLevelType.HARD);
    }};

    public static HashMap<GameLevelType, Integer> gameMoneyMap = new HashMap() {{
        put(GameLevelType.EASY, 1000);
        put(GameLevelType.NORMAL, 500);
        put(GameLevelType.HARD, 100);
    }};

    public static HashMap getGameMoneyMap() {
        return gameMoneyMap;
    }

    Timer timer = new Timer();

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

    public void modalToast(AnchorPane root, String msg) {
        System.out.println(root);
        if (root != null) {
            TextFlow toast = new TextFlow();
            Text text = new Text(msg);
            text.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
            text.setFill(Color.GHOSTWHITE);

            toast.getChildren().add(text);
            toast.setTextAlignment(TextAlignment.CENTER);
            root.setTopAnchor(toast, 80.0);
            root.setLeftAnchor(toast, 0.0);
            root.setRightAnchor(toast, 0.0);
            root.getChildren().add(toast);

            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            FadeTransition transition = new FadeTransition(Duration.millis(500), toast);
                            transition.setFromValue(1);
                            transition.setToValue(0);
                            transition.setOnFinished((event) -> {
                                root.getChildren().remove(toast);
                            });
                            transition.play();
                        }
                    });
                }
            };
            timer.schedule(task, 1000l);
        }
    }

    public void initWelcomeScene(Stage stage, AnchorPane root) {
        root = new AnchorPane();
        root.setBackground(new Background(myBI));
        root.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        Scene scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        WelcomeSceneWrapper welcomeSceneWrapper = new WelcomeSceneWrapper(stage, root, scene);
        stage.setScene(scene);
    }

    public void initConfigScene(Stage stage, AnchorPane root) {
        root = new AnchorPane();
        root.setBackground(new Background(myBI));
        root.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        Scene scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        ConfigSceneWrapper configSceneWrapper = new ConfigSceneWrapper(stage, root, scene);
        stage.setScene(scene);
    }

    public void initGameScene(Stage stage, AnchorPane root) {
        root = new AnchorPane();
        root.setBackground(new Background(myBI));
        root.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        Scene scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        System.out.println("qwer 1" + getGameLevel());
        GameSceneWrapper gameSceneWrapper = new GameSceneWrapper(stage, root, scene);
        System.out.println("qwer 2" + getGameLevel());
        stage.setScene(scene);
    }
}
