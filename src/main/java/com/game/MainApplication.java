package com.game;

import com.game.config.Config;
import com.main.model.GameLevelType;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
import java.util.Timer;
import java.util.TimerTask;

public class MainApplication extends Application {
    private static Stage primaryStage;
    private AnchorPane root;
    private Scene scene;

    public ArrayList<GameLevelType> gameLevelTypeArr = new ArrayList<GameLevelType>() {{
        add(GameLevelType.EASY);
        add(GameLevelType.NORMAL);
        add(GameLevelType.HARD);
    }};

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
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new AnchorPane();
        this.scene = new Scene(this.root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);

        this.primaryStage.setTitle("v2");
        this.primaryStage.setResizable(false);

        initWelcomeScene();
//        initGameScene();
//        initConfigScene();
        primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public void initWelcomeScene() {
        root = new AnchorPane();
        root.setBackground(new Background(myBI));
        root.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        this.scene = new Scene(this.root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        WelcomeSceneWrapper welcomeSceneWrapper = new WelcomeSceneWrapper(primaryStage, root, scene);
        primaryStage.setScene(scene);
    }

    public void initConfigScene() {
        root = new AnchorPane();
        root.setBackground(new Background(myBI));
        root.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        this.scene = new Scene(this.root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        ConfigSceneWrapper configSceneWrapper = new ConfigSceneWrapper(primaryStage, root, scene);
        primaryStage.setScene(scene);
    }

    public void initGameScene() {
        root = new AnchorPane();
        root.setBackground(new Background(myBI));
        root.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        this.scene = new Scene(this.root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT, Color.GHOSTWHITE);
        GameSceneWrapper gameSceneWrapper = new GameSceneWrapper(primaryStage, root, scene);
        primaryStage.setScene(scene);
    }

    Timer timer = new Timer();

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

            timer.schedule(task,1000l);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
